package com.example.solvechallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Activity_World extends AppCompatActivity {

    private Button enter_section_btn;

    public void switchToNextActivity(View view) {
        Intent intent = new Intent(this, Activity_Section.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world);
        this.getSupportActionBar().hide();

        enter_section_btn = (Button) findViewById(R.id.btn_entersection_World); // type name Activity_name

        enter_section_btn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                switchToNextActivity(v);

            }

        });

    }
}


