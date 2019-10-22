package com.example.solvechallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Activity_Section extends AppCompatActivity {

    private Button enter_level_btn;

    public void switchToNextActivity(View view) {
        Intent intent = new Intent(this, Activity_Level.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);
        this.getSupportActionBar().hide();

        enter_level_btn = (Button) findViewById(R.id.btn_enterlevel_Section); // type name Activity_name

        enter_level_btn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                switchToNextActivity(v);

            }

        });

    }
}


