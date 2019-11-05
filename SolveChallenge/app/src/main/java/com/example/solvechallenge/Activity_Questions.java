package com.example.solvechallenge;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Activity_Questions extends AppCompatActivity {

    TextView tv_question;
    Button btn_choice1;
    Button btn_choice2;
    Button btn_choice3;
    Button btn_choice4;
    private ArrayList<Button> btns = new ArrayList<>();
    TextView tv_score;

    int current_world_int = App_Data.getWorld();
    int current_section_int = App_Data.getSection();
    int current_level_int = App_Data.getLevel();
    int upperbound_level_int = App_Data.getLevel_upperbound();

    String current_world_str = Config.getWorlds()[current_world_int];
    String current_section_str = Config.getSections().get(current_world_int).get(current_section_int);
    String current_level_str = Config.getLevels()[current_level_int];
    String char_selected = App_Data.getCharacter();

    private JSONArray questionsInJson;
    private JSONObject currentAnswerResponse;
    private int no_questions = 6;
    private int index_of_question = 0;
    private int score = 0;

    private int score_gained;
    private Boolean ifCorrect;
    private String answerMessage;
    private String current_question_id;
    private String choiceInAlpha;

    public String getChoiceInAlpha() {
        return choiceInAlpha;
    }

    public void setChoiceInAlpha(String choiceInAlpha) {
        this.choiceInAlpha = choiceInAlpha;
    }

    public String getCurrent_question_id() {
        return current_question_id;
    }

    public void setCurrent_question_id(String current_question_id) {
        this.current_question_id = current_question_id;
    }

    public int getScore_gained() {
        return score_gained;
    }

    public void setScore_gained(int score_gained) {
        this.score_gained = score_gained;
    }

    public Boolean getIfCorrect() {
        return ifCorrect;
    }

    public void setIfCorrect(Boolean ifCorrect) {
        this.ifCorrect = ifCorrect;
    }

    public String getAnswerMessage() {
        return answerMessage;
    }

    public void setAnswerMessage(String answerMessage) {
        this.answerMessage = answerMessage;
    }

    public int getIndex_of_question() {
        return index_of_question;
    }

    public void setIndex_of_question(int index_of_question) {
        this.index_of_question = index_of_question;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCurrentAnswerResponse(JSONObject currentAnswerResponse) {
        this.currentAnswerResponse = currentAnswerResponse;
    }

    public void checkAnswer(int choice){

        switch (choice){
            case 0: setChoiceInAlpha("A"); break;
            case 1: setChoiceInAlpha("B"); break;
            case 2: setChoiceInAlpha("C"); break;
            case 3: setChoiceInAlpha("D");
        }

        //check the answer

        OkHttpClient client = new OkHttpClient();
        String url = Config.baseUrl + "answer";
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();

        try {
            JSONObject question = (JSONObject) questionsInJson.get(index_of_question);
            setCurrent_question_id(question.get("id").toString());
        } catch(JSONException e){
            e.printStackTrace();
        }
        System.out.println("############");
        System.out.println(App_Data.getUserId().toString());
        System.out.println(getCurrent_question_id());
        System.out.println(getChoiceInAlpha());
        System.out.println();
        System.out.println();
        System.out.println();

        JSONObject newAnswer = new JSONObject();
        try {
            newAnswer.put("studentId", App_Data.getUserId().toString());
            newAnswer.put("questionId", getCurrent_question_id());
            newAnswer.put("choice", getChoiceInAlpha());
            newAnswer.put("correct", "false");
            newAnswer.put("reward", "0");
            newAnswer.put("mode", "Q");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(Config.JSON, newAnswer.toString());

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Activity_Questions.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Activity_Questions.this, "Failed...", Toast.LENGTH_SHORT).show();
                    }
                });
                e.printStackTrace();
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        JSONObject r = new JSONObject(response.body().string());
                        System.out.println("####");
                        System.out.println(r);
                        setCurrentAnswerResponse(r);

                        setIfCorrect(Boolean.parseBoolean(currentAnswerResponse.get("correct").toString()));
                        setScore_gained(Integer.parseInt(currentAnswerResponse.get("mark").toString()));
                        setAnswerMessage(currentAnswerResponse.get("message").toString());

                        Activity_Questions.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),getAnswerMessage(),Toast.LENGTH_SHORT).show();
                                if (getIfCorrect()){
                                    setIndex_of_question(getIndex_of_question()+1);
                                    setScore(getScore()+getScore_gained());
                                    showNextQuestion();
                                }
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Activity_Questions.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Activity_Questions.this, "Failed..", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }

    public void showNextQuestion(){
        if ((index_of_question==no_questions-1 && getScore()==75) || (index_of_question<=no_questions-2)){
            try {
                JSONObject question = (JSONObject) questionsInJson.get(index_of_question);
                String description = question.get("description").toString();
                String choice1 = question.get("choiceA").toString();
                String choice2 = question.get("choiceB").toString();
                String choice3 = question.get("choiceC").toString();
                String choice4 = question.get("choiceD").toString();
                tv_question.setText(description);
                btn_choice1.setText(choice1);
                btn_choice2.setText(choice2);
                btn_choice3.setText(choice3);
                btn_choice4.setText(choice4);
                tv_score.setText(Integer.toString(getScore()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else {

            System.out.println("***" + Integer.toString(current_level_int) + Integer.toString(upperbound_level_int));

            if (current_level_int < upperbound_level_int){
                Intent myIntent = new Intent(Activity_Questions.this, Activity_Level.class);
                startActivity(myIntent);
            }

            else {
                OkHttpClient client = new OkHttpClient();
                String url = Config.baseUrl + "status/update";
                HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
                httpBuilder.addQueryParameter("studentId", App_Data.getUserId().toString());
                httpBuilder.addQueryParameter("character", App_Data.getCharacter());

                Request request = new Request.Builder()
                        .url(httpBuilder.build())
                        .build();

                System.out.println(request.toString());

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Activity_Questions.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Activity_Questions.this, "Failed...", Toast.LENGTH_SHORT).show();
                            }
                        });
                        e.printStackTrace();
                    }

                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject r = new JSONObject(response.body().string());
                                System.out.println(r);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } else {
                            Activity_Questions.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Activity_Questions.this, "Failed............", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });

                Intent myIntent = new Intent(Activity_Questions.this, Activity_Level.class);
                startActivity(myIntent);
            }
        }
    }

    public void setOnClick(final Button btn, final int i){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(i);
            }
        });
    }

    public void setQuestionsInJson(JSONArray questionsInJson) {
        this.questionsInJson = questionsInJson;
    }


    private void getQuestionsInJson(String character, String world, String section, String level) {

        JSONObject r;

        OkHttpClient client = new OkHttpClient();
        String url = Config.baseUrl + "question/stage";
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
        httpBuilder.addQueryParameter("level", level);
        httpBuilder.addQueryParameter("section", section);
        httpBuilder.addQueryParameter("world", world);
        httpBuilder.addQueryParameter("character", character);

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Activity_Questions.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Activity_Questions.this, "Failed...", Toast.LENGTH_SHORT).show();
                    }
                });
                e.printStackTrace();
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        JSONArray r = new JSONArray(response.body().string());
                        System.out.println("################ response body");
                        System.out.println(r);
                        System.out.println(r.get(0));
                        System.out.println(r.get(1));
                        System.out.println(r.get(2));
                        System.out.println(r.get(3));
                        JSONObject question_1 = (JSONObject) r.get(0);
                        System.out.println(question_1);
                        System.out.println(question_1.get("description"));
                        System.out.print(r);
                        setQuestionsInJson(r);

                        Activity_Questions.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showNextQuestion();
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Activity_Questions.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Activity_Questions.this, "Failed..", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        this.getSupportActionBar().hide();

        tv_question = (TextView) findViewById(R.id.tv_question_Questions);
        btn_choice1 = (Button) findViewById(R.id.btn_choice1_Questions);
        btn_choice2 = (Button) findViewById(R.id.btn_choice2_Questions);
        btn_choice3 = (Button) findViewById(R.id.btn_choice3_Questions);
        btn_choice4 = (Button) findViewById(R.id.btn_choice4_Questions);
        tv_score = (TextView) findViewById(R.id.tv_score_Questions);

        btns.add(btn_choice1);
        btns.add(btn_choice2);
        btns.add(btn_choice3);
        btns.add(btn_choice4);


        // Set button listener
        for (int i = 0; i < 4; i++) {
            Button btn = btns.get(i);
            setOnClick(btn, i);
        }

        // Get Questions
        getQuestionsInJson(char_selected, current_world_str, current_section_str, current_level_str);

    }

}







