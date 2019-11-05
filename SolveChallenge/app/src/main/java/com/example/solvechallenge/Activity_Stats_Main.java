package com.example.solvechallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_Stats_Main extends AppCompatActivity {

    Button leadDeveloper;
    Button productManager;
    Button qualityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        getSupportActionBar().hide();

        leadDeveloper = findViewById(R.id.btn_developer_stats);
        leadDeveloper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Stats_Main.this, Activity_Stats_Second.class);
                intent.putExtra("character_choice" , "Lead Developer");//'Quality Manager
                startActivity(intent);
            }
        });

        productManager = findViewById(R.id.btn_product_stats);
        productManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Stats_Main.this, Activity_Stats_Second.class);
                intent.putExtra("character_choice" , "Product Manager");//'Quality Manager
                startActivity(intent);
            }
        });

        qualityManager = findViewById(R.id.btn_QA_stats);
        qualityManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Stats_Main.this, Activity_Stats_Second.class);
                intent.putExtra("character_choice" , "Quality Manager");//'Quality Manager
                startActivity(intent);
            }
        });
    }
}
