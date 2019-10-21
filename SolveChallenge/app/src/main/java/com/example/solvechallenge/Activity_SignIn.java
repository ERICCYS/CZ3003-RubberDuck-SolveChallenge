package com.example.solvechallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Activity_SignIn extends AppCompatActivity {

    private  Button sign_in_btn;

    public void switchToNextActivity(View view) {
        Intent intent = new Intent(this, Activity_SelectCharacter.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        this.getSupportActionBar().hide();


        sign_in_btn = (Button) findViewById(R.id.btn_sign_in_SignIn); // type name Activity_name

        sign_in_btn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                switchToNextActivity(v);

            }

        });

    }

}
