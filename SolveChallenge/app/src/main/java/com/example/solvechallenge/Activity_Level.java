package com.example.solvechallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Activity_Level extends AppCompatActivity {

    private Button start_easy_question_btn;
    private Button start_mid_question_btn;
    private Button start_hard_question_btn;

    public void switchToNextActivity(View view) {
        Intent intent = new Intent(this, Activity_Questions.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        this.getSupportActionBar().hide();

        start_easy_question_btn = (Button) findViewById(R.id.imgbtn_easylevel_Level); // type name Activity_name
        start_mid_question_btn = (Button) findViewById(R.id.imgbtn_midlevel_Level);
        start_hard_question_btn = (Button) findViewById(R.id.imgbtn_hardlevel_Level);


        start_easy_question_btn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                switchToNextActivity(v);

            }

        });

    }
}


