package com.example.solvechallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Activity_SelectCharacter extends AppCompatActivity {

    private Button select_char_btn;

    public void switchToNextActivity(View view) {
        Intent intent = new Intent(this, Activity_World.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_character);
        this.getSupportActionBar().hide();

        select_char_btn = (Button) findViewById(R.id.btn_sc_SelectChar); // type name Activity_name

        select_char_btn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                switchToNextActivity(v);

            }

        });

    }
}


