package com.example.solvechallenge;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.solvechallenge.ui.SelectCharacter.Activity_Section;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Activity_Level extends AppCompatActivity {

    private FloatingActionButton go_back_btn;
    private Button start_easy_question_btn;
    private Button start_mid_question_btn;
    private Button start_hard_question_btn;
    private int level_upperbound;
    private int current_section;
    private int section_upperbound;
    private int world_upperbound;
    private int current_world;

    private ArrayList<Button> btns = new ArrayList<>();

    public void switchToNextActivity(View view) {
        Intent intent = new Intent(this, Activity_Questions.class);
        startActivity(intent);
    }

    private void setOnClick(final Button btn, final Integer i) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App_Data.setLevel(i);
                App_Data.printAllData();
                switchToNextActivity(v);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        System.out.println("###############################Level page on load###############################");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        this.getSupportActionBar().hide();

        start_easy_question_btn = (Button) findViewById(R.id.imgbtn_easylevel_Level); // type name Activity_name
        start_mid_question_btn = (Button) findViewById(R.id.imgbtn_midlevel_Level);
        start_hard_question_btn = (Button) findViewById(R.id.imgbtn_hardlevel_Level);
        go_back_btn = (FloatingActionButton) findViewById(R.id.btn_back_level);
        go_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Level.this, Activity_Section.class);
                startActivity(intent);
            }
        });

        btns.add(start_easy_question_btn);
        btns.add(start_mid_question_btn);
        btns.add(start_hard_question_btn);

        OkHttpClient client = new OkHttpClient();
        String url = Config.baseUrl + "world/initialize";
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
        httpBuilder.addQueryParameter("character", App_Data.getCharacter());
        httpBuilder.addQueryParameter("studentId", App_Data.getUserId().toString());

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .addHeader("Authorization", App_Data.getAccessToken())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Activity_Level.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Activity_Level.this, "Failed...", Toast.LENGTH_SHORT).show();
                    }
                });
                e.printStackTrace();
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        JSONObject r = new JSONObject(response.body().string());
                        App_Data.setWorld_upperbound(Integer.parseInt(r.get("world").toString()));
                        App_Data.setSection_upperbound(Integer.parseInt(r.get("section").toString()));
                        App_Data.setLevel_upperbound(Integer.parseInt(r.get("level").toString()));

                        Activity_Level.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                level_upperbound = App_Data.getLevel_upperbound();
                                current_section = App_Data.getSection();
                                section_upperbound = App_Data.getSection_upperbound();
                                world_upperbound = App_Data.getWorld_upperbound();
                                current_world = App_Data.getWorld();



                                for (int i = 0; i < 3; i++) {
                                    Button btn = btns.get(i);
                                    if (current_section == section_upperbound && current_world==world_upperbound) {
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
                        });

                    }catch (JSONException e) {
                        e.printStackTrace();
                    }

                    App_Data.printAllData();


                } else {
                    Activity_Level.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Activity_Level.this, "Failed..", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });



    }

}


