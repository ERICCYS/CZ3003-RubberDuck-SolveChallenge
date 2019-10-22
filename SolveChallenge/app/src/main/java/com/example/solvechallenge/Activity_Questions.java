package com.example.solvechallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

public class Activity_Questions extends AppCompatActivity {

    TextView tv_question = (TextView) findViewById(R.id.tv_question_Questions);
    Button btn_choice1 = (Button)findViewById(R.id.btn_choice1_Questions);
    Button btn_choice2 = (Button)findViewById(R.id.btn_choice2_Questions);
    Button btn_choice3 = (Button)findViewById(R.id.btn_choice3_Questions);
    Button btn_choice4 = (Button)findViewById(R.id.btn_choice4_Questions);
    TextView tv_score = (TextView)findViewById(R.id.tv_score_Questions);

    private String answer;
    private JSONObject question_data;
    private int no_questions=6;
    private int index_of_question=0;
    private int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        this.getSupportActionBar().hide();

//        while (index_of_question < no_questions){
//
//            App_Data.setNo_question(index_of_question);
//
//            // todo question_data = getQuestion(); // get data from backend
//
//            answer = q.get("answer").tostring();
//
//            tv_question.setText(q.get("question").tostring());
//            btn_choice1.setText(q.get("choice1").tostring());
//            btn_choice2.setText(q.get("choice2").tostring());
//            btn_choice3.setText(q.get("choice3").tostring());
//            btn_choice4.setText(q.get("choice4").tostring());
//
//            btn_choice1.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View view){
//                    //My logic for Button goes in here
//
//                    if (btn_choice1.getText() == answer){
//                        score = score + 1;
//
//                        tv_score.setText(score);
//
//                        updateQuestion();
//                        //This line of code is optiona
//                        Toast.makeText(Activity_Questions.this, "correct! lol", Toast.LENGTH_SHORT).show();
//                    }else {
//                        Toast.makeText(Activity_Questions.this, "wrong.. try again", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//
//        }


    }



}





