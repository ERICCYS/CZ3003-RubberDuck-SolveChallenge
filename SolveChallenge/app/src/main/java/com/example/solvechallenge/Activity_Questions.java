package com.example.solvechallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Activity_Questions extends AppCompatActivity {

    TextView tv_question = (TextView) findViewById(R.id.tv_question_Questions);
    Button brn_choice1 = (Button)findViewById(R.id.btn_choice1_Questions);
    Button btn_choice2 = (Button)findViewById(R.id.btn_choice2_Questions);
    Button btn_choice3 = (Button)findViewById(R.id.btn_choice3_Questions);
    Button btn_choice4 = (Button)findViewById(R.id.btn_choice4_Questions);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        this.getSupportActionBar().hide();


//        quenstion_answer_data = {}; // json



    }


}

