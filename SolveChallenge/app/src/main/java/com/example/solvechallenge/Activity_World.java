package com.example.solvechallenge;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class Activity_World extends AppCompatActivity {

    private Button enter_intro_btn ;
    private Button enter_analysis_btn ;
    private Button enter_design_btn ;
    private Button enter_testing_btn ;
    private Button enter_maintenance_btn;

    private String[] worlds = {"Requirement Gathering and Analysis", "\nDesign", "Implementation", "Testing and Deployment", "Maintenance"};

    private String[] config_worlds = Config.getWorlds();
    private ArrayList<Button> btns = new ArrayList<>();
    private int world_upperbound = App_Data.getWorld_upperbound();

    public void switchToNextActivity(View view) {
        Intent intent = new Intent(this, Activity_Section.class);
        startActivity(intent);
    }

    private void setOnClick(final Button btn, final Integer i){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App_Data.setWorld(i);
                App_Data.printAllData();
                switchToNextActivity(v);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        this.getSupportActionBar().hide();

        enter_intro_btn = (Button) findViewById(R.id.btn_intro_World);
        enter_analysis_btn = (Button) findViewById(R.id.btn_analysis_World);
        enter_design_btn = (Button) findViewById(R.id.btn_design_World);
        enter_testing_btn = (Button) findViewById(R.id.btn_testing_World);
        enter_maintenance_btn = (Button) findViewById(R.id.btn_maintainance_World);
        btns.add(enter_intro_btn);
        btns.add(enter_analysis_btn);
        btns.add(enter_design_btn);
        btns.add(enter_testing_btn);
        btns.add(enter_maintenance_btn);
//        enter_maintenance_btn.setText("Test");
//        btns.get(1).setText("blabalbal");

        for ( int i = 0; i < 5; i++) {
            Button btn = btns.get(i);
            btn.setText(worlds[i]);
            if (i <= world_upperbound) {
                btn.setTextColor(Color.parseColor("#000000"));
                setOnClick(btn, i);
            } else {
                btn.setTextColor(Color.parseColor("#80DDDDDD"));
            }

        }


    }
}


