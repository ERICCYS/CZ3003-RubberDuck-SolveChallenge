package com.example.solvechallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class Activity_Level extends AppCompatActivity {

    private Button start_easy_question_btn;
    private Button start_mid_question_btn;
    private Button start_hard_question_btn;
    private int current_section = App_Data.getSection();
    private int current_level = App_Data.getLevel();
    private int level_upperbound = App_Data.getLevel_upperbound();
    private int section_upperbound = App_Data.getSection_upperbound();

    private ArrayList<Button> btns = new ArrayList<>();

    public void switchToNextActivity(View view) {
        Intent intent = new Intent(this, Activity_Questions.class);
        startActivity(intent);
    }

    private void setOnClick(final Button btn, final Integer i) {
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
        setContentView(R.layout.activity_level);
        this.getSupportActionBar().hide();

        start_easy_question_btn = (Button) findViewById(R.id.imgbtn_easylevel_Level); // type name Activity_name
        start_mid_question_btn = (Button) findViewById(R.id.imgbtn_midlevel_Level);
        start_hard_question_btn = (Button) findViewById(R.id.imgbtn_hardlevel_Level);

        btns.add(start_easy_question_btn);
        btns.add(start_mid_question_btn);
        btns.add(start_hard_question_btn);

        for (int i = 0; i < 3; i++) {
            Button btn = btns.get(i);
            if (current_section == section_upperbound) {
                if (i <= level_upperbound) {
                    setOnClick(btn, i);
                } else {
                    //                btn.setBackgroundColor(Color.parseColor("#c4ffffff"));
                    btn.setAlpha(0.2f);
                }
            } else {
                setOnClick(btn, i);
            }

        }

    }
}


