package com.example.solvechallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Activity_World extends AppCompatActivity {

    private Button enter_intro_btn = (Button) findViewById(R.id.btn_intro_World);;
    private Button enter_analysis_btn = (Button) findViewById(R.id.btn_analysis_World);
    private Button enter_design_btn = (Button) findViewById(R.id.btn_design_World);
    private Button enter_testing_btn = (Button) findViewById(R.id.btn_testing_World);
    private Button enter_maintainance_btn = (Button) findViewById(R.id.btn_maintainance_World);


    public void switchToNextActivity(View view) {
        Intent intent = new Intent(this, Activity_Section.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world);
        this.getSupportActionBar().hide();




        // todo get





//        enter_intro_btn.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//
//                if (0<= world_upperbound){
//                    switchToNextActivity(v);
//                    App_Data.setWorld(0);
//                }
//
//                else{
//                    Toast.makeText(Activity_World.this, "it is locked", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//
//        enter_analysis_btn.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                switchToNextActivity(v);
//                App_Data.setWorld("analysis");
//            }
//        });
//
//        enter_design_btn.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                switchToNextActivity(v);
//                App_Data.setWorld("design");
//            }
//        });
//
//        enter_testing_btn.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                switchToNextActivity(v);
//                App_Data.setWorld("testing");
//            }
//        });
//
//        enter_maintainance_btn.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                switchToNextActivity(v);
//                App_Data.setWorld("maintenance");
//            }
//        });
//
    }
}


