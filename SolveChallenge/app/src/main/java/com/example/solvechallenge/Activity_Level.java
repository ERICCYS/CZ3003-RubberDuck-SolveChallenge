package com.example.solvechallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Activity_Level extends AppCompatActivity {

    private Button start_question_btn;

    public void switchToNextActivity(View view) {
        Intent intent = new Intent(this, Activity_Questions.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        this.getSupportActionBar().hide();

        start_question_btn = (Button) findViewById(R.id.btn_startquestions_Level); // type name Activity_name

        start_question_btn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                switchToNextActivity(v);

            }

        });

    }
}


