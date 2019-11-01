package com.example.solvechallenge;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Activity_Section extends AppCompatActivity {

    private Button enter_sec1_btn;
    private Button enter_sec2_btn;
    private Button enter_sec3_btn;
    private int current_world = App_Data.getWorld();
    private int world_upperbound = App_Data.getWorld_upperbound();
    private ArrayList<Button> btns = new ArrayList<>();
    private int section_upperbound = App_Data.getSection_upperbound();
    private ArrayList<String> sections = Config.getSections().get(current_world);

    public void switchToNextActivity(View view) {
        Intent intent = new Intent(this, Activity_Level.class);
        startActivity(intent);
    }

    private void setOnClick(final Button btn, final Integer i){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App_Data.setSection(i);
                App_Data.printAllData();
                switchToNextActivity(v);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);
        enter_sec1_btn = findViewById(R.id.btn_sec1_section);
        enter_sec2_btn = findViewById(R.id.btn_sec2_section);
        enter_sec3_btn = findViewById(R.id.btn_sec3_section);
        this.getSupportActionBar().hide();

        btns.add(enter_sec1_btn);
        btns.add(enter_sec2_btn);
        btns.add(enter_sec3_btn);


        for ( int i = 0; i < 3; i++) {
            Button btn = btns.get(i);
            btn.setText(sections.get(i));
            if (current_world==world_upperbound) {
                if (i <= section_upperbound) {
                    setOnClick(btn, i);
                } else {
                    btn.setAlpha(0.2f);
                }
            }else{
                setOnClick(btn, i);
            }

        }

    }
}


