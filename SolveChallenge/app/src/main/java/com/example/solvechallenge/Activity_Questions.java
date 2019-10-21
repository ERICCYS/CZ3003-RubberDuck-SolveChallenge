package com.example.solvechallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Activity_Questions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        this.getSupportActionBar().hide();
    }
}
