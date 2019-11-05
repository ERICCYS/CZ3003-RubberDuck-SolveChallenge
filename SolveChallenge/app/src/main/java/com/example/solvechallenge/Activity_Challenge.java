package com.example.solvechallenge;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

public class Activity_Challenge extends AppCompatActivity {


    private ArrayList<Integer> questionIds;
    private int no_questions;

    private JSONObject questionInJson;
    private JSONArray questionsInJson;
    private int index_of_question = 0;
    private String correct_answer;

    TextView tv_question;
    Button btn_choice1;
    Button btn_choice2;
    Button btn_choice3;
    Button btn_choice4;
    private ArrayList<Button> btns = new ArrayList<>();

    private String choiceInAlpha;
    private int no_correct = 0;
    private int no_wrong = 0;

    public String getChoiceInAlpha() {
        return choiceInAlpha;
    }

    public void setChoiceInAlpha(String choiceInAlpha) {
        this.choiceInAlpha = choiceInAlpha;
    }

    public JSONObject getQuestionInJson() {
        return questionInJson;
    }

    public void setQuestionInJson(JSONObject questionInJson) {
        this.questionInJson = questionInJson;
    }

    public void setQuestionsInJson(JSONArray questionsInJson) {
        this.questionsInJson = questionsInJson;
    }

    public ArrayList<Integer> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(ArrayList<Integer> questionIds) {
        this.questionIds = questionIds;
    }

    public int getNo_questions() {
        return no_questions;
    }

    public void setNo_questions(int no_questions) {
        this.no_questions = no_questions;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public ArrayList<Button> getBtns() {
        return btns;
    }

    public void setBtns(ArrayList<Button> btns) {
        this.btns = btns;
    }

    public JSONArray getQuestionsInJson() {
        return questionsInJson;
    }

    public void addQuestionsInJson(JSONObject questionInJson) {
        this.questionsInJson.put(questionInJson);
    }

    public int getIndex_of_question() {
        return index_of_question;
    }

    public void setIndex_of_question(int index_of_question) {
        this.index_of_question = index_of_question;
    }

    public void setOnClick(final Button btn, final int i){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(i);
            }
        });
    }

    public void checkAnswer(int choice) {

        switch (choice) {
            case 0:
                setChoiceInAlpha("A");
                break;
            case 1:
                setChoiceInAlpha("B");
                break;
            case 2:
                setChoiceInAlpha("C");
                break;
            case 3:
                setChoiceInAlpha("D");
        }

        try{
            JSONObject question = questionsInJson.getJSONObject(index_of_question);
            if (getChoiceInAlpha() == question.get("correctChoice")){
                no_correct+=1;
            }
            else {
                no_wrong += 1;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        setIndex_of_question(getIndex_of_question()+1);
        showNextQuestion();

    }

    public void showNextQuestion() {
        if ((index_of_question != no_questions - 1)) {
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
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else{
            Toast.makeText(Activity_Challenge.this, String.format("Correct %d, Wrong %d", no_correct, no_wrong), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Activity_Challenge.this, Activity_World.class);
            startActivity(intent);
        }
    }


    public void getQuestions(ArrayList<Integer> questionIds){

        OkHttpClient client = new OkHttpClient();
        String url = Config.baseUrl + "questions";
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();

        JSONArray questionIdObject = new JSONArray();
        ArrayList<Long> questionId = new ArrayList<>();

        for (int i=0; i<no_questions; i++){
            questionIdObject.put(Long.parseLong(Integer.toString(questionIds.get(i))));
        }

        RequestBody requestBody = RequestBody.create(Config.JSON, questionIdObject.toString());

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .addHeader("Authorization", App_Data.getAccessToken())
                .post(requestBody)
                .build();

        System.out.println(request);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("onfailure");
                Activity_Challenge.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Activity_Challenge.this, "Failed...", Toast.LENGTH_SHORT).show();
                    }
                });
                e.printStackTrace();
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {

                    try {
//                        JSONArray questionsInJson = new JSONArray();
//
//                        for (int i=0; i<5; i++){
//                            JSONObject questionInJson = new JSONObject();
//                            questionInJson.put("description", "What Is A Static Analysis Tool?");
//                            questionInJson.put("choiceA", "A tool is used to analyse the software while running the program.");
//                            questionInJson.put("choiceB", "It is the analysis of computer software that is performed without actually executing programs");
//                            questionInJson.put("choiceC", "It is the to execute the software and test for error");
//                            questionInJson.put("choiceD", "All of the above");
//                            questionInJson.put("correctChoice", "C");
//                            questionsInJson.put(questionInJson);
//                        }

                        JSONObject r = new JSONObject(response.body().string());
                        System.out.println("####");
                        System.out.println(r);

//                        setQuestionsInJson(questionsInJson);

                        setQuestionsInJson(new JSONArray(response.body().string()));

                        Activity_Challenge.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showNextQuestion();
                            }});

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                } else {
                    System.out.println("not successful");
                    Activity_Challenge.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Activity_Challenge.this, "Failed............", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__challenge);

        Bundle extras = getIntent().getExtras();
        setQuestionIds(extras.getIntegerArrayList("question_ids"));
        System.out.println("@@@@@@@@@@@@");
        System.out.println(getQuestionIds());
        setNo_questions(getQuestionIds().size());


        tv_question = (TextView) findViewById(R.id.tv_question_Challenge);
        btn_choice1 = (Button) findViewById(R.id.btn_choice1_Challenge);
        btn_choice2 = (Button) findViewById(R.id.btn_choice2_Challenge);
        btn_choice3 = (Button) findViewById(R.id.btn_choice3_Challenge);
        btn_choice4 = (Button) findViewById(R.id.btn_choice4_Challenge);

        btns.add(btn_choice1);
        btns.add(btn_choice2);
        btns.add(btn_choice3);
        btns.add(btn_choice4);

        for (int i = 0; i < 4; i++) {
            Button btn = btns.get(i);
            setOnClick(btn, i);
        }

        getQuestions(questionIds);
//
//        System.out.println("question in json outside");
//        System.out.println(questionsInJson);

    }

}
