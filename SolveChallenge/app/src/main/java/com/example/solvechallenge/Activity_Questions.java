package com.example.solvechallenge;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
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
import okhttp3.MultipartBody;
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

    String current_world_str = Config.getWorlds()[current_world_int];
    String current_section_str = Config.getSections().get(current_world_int).get(current_section_int);
    String current_level_str = Config.getLevels()[current_level_int];
    String char_selected = App_Data.getCharacter();

    private JSONArray questionsInJson;
    private JSONObject currentAnswerResponse;
    private int no_questions = 6;
    private int index_of_question = 0;
    private int score = 0;

    public void setCurrentAnswerResponse(JSONObject currentAnswerResponse) {
        this.currentAnswerResponse = currentAnswerResponse;
    }

    public void checkAnswer(int choice){

        String choiceInAlpha;
        String question_id = "";
        int mark = 0;
        Boolean answerCorrectness = false;
        String message = "";


        if (choice==1) choiceInAlpha="A";
        else if (choice==2) choiceInAlpha="B";
        else if (choice==3) choiceInAlpha="C";
        else choiceInAlpha="D";

        //TODO check the answer

        OkHttpClient client = new OkHttpClient();
        String url = Config.baseUrl + "answer";
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();

        try {
            JSONObject question = (JSONObject) questionsInJson.get(index_of_question);
            question_id = question.get("id").toString();
        } catch(JSONException e){
            e.printStackTrace();
        }

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("studentId", App_Data.getUserId().toString())
                .addFormDataPart("questionId", question_id)
                .addFormDataPart("choice", choiceInAlpha)
                .addFormDataPart("correct", "false")
                .addFormDataPart("reward", "0")
                .addFormDataPart("mode", "Q")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", App_Data.getAccessToken())
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
                        setCurrentAnswerResponse(r);
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

        try {
            answerCorrectness = Boolean.parseBoolean(currentAnswerResponse.get("correct").toString());
            mark = Integer.parseInt(currentAnswerResponse.get("mark").toString());
            message = currentAnswerResponse.get("message").toString();
        }catch (JSONException e) {
            e.printStackTrace();
        }

        if (answerCorrectness){
            index_of_question=index_of_question+1;
            score = score+mark;
            showNextQuestion();
        }
        
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

    }

    public void showNextQuestion(){
        if (index_of_question>no_questions){
            finish();
        }
        else {
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
                tv_score.setText(score);
            } catch (JSONException e) {
                e.printStackTrace();
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
                .addHeader("Authorization", App_Data.getAccessToken())
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
//                        System.out.println("################ response body");
//                        System.out.println(r);
//                        System.out.println(r.get(0));
//                        System.out.println(r.get(1));
//                        System.out.println(r.get(2));
//                        System.out.println(r.get(3));
//                        JSONObject question_1 = (JSONObject) r.get(0);
//                        System.out.println(question_1);
//                        System.out.println(question_1.get("description"));
                        setQuestionsInJson(r);
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

        // Get Questions

        getQuestionsInJson(char_selected, current_world_str, current_section_str, current_level_str);

        // Set button listener

        for (int i = 0; i < 4; i++) {
            Button btn = btns.get(i);
            setOnClick(btn, i);
        }

        showNextQuestion();

    }

}







