package com.example.solvechallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.solvechallenge.StatsQuestion.Activity_Stats_Question;
import com.example.solvechallenge.StatsSection.Activity_Stats_Section;
import com.example.solvechallenge.StatsWorld.Activity_Stats_World;

public class Activity_Stats_Second extends AppCompatActivity {

    Button viewWorld;
    Button viewSection;
    Button viewQuestion;
    String characterChoice;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_second);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        characterChoice = intent.getStringExtra("character_choice");

        title = findViewById(R.id.txtview_title_stats);
        title.setText(characterChoice);

        viewWorld = findViewById(R.id.btn_world_stats_second);
        viewWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Stats_Second.this, Activity_Stats_World.class);
                intent.putExtra("character_choice", characterChoice);
                startActivity(intent);
            }
        });

        viewSection = findViewById(R.id.btn_section_stats_second);
        viewSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Stats_Second.this, Activity_Stats_Section.class);
                intent.putExtra("character_choice", characterChoice);
                startActivity(intent);
            }
        });

        viewQuestion = findViewById(R.id.btn_question_stats_second);
        viewQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Stats_Second.this, Activity_Stats_Question.class);
                intent.putExtra("character_choice", characterChoice);
                startActivity(intent);
            }
        });
    }
}
