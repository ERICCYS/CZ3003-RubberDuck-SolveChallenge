package com.example.solvechallenge.StatsSection;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.solvechallenge.Config;
import com.example.solvechallenge.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Activity_Stats_Section extends AppCompatActivity {

    private JSONArray sections;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter statsSectionAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private String characterChoice;

    private void getRankingJson() {

        // TODO: Integrate with backend
        OkHttpClient client = new OkHttpClient();
        String url = Config.baseUrl + "statistic/section";
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();

        httpBuilder.addQueryParameter("character", characterChoice);
        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Activity_Stats_Section.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Activity_Stats_Section.this, "Failed...", Toast.LENGTH_SHORT).show();
                    }
                });
                e.printStackTrace();
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        JSONArray r = new JSONArray(response.body().string());
                        System.out.println("################ response body");
                        System.out.println(r);
                        System.out.println(r.get(0));
                        System.out.println(r.get(1));
                        System.out.println(r.get(2));
                        System.out.println(r.get(3));
//                        JSONObject question_1 = (JSONObject) r.get(0);
//                        System.out.println(question_1);
//                        System.out.println(question_1.get("description"));
                        System.out.print(r);
                        setSections(r);

                        Activity_Stats_Section.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Set up recylcerview
                                recyclerView = findViewById(R.id.view_recycler_stats_section);
                                recyclerView.setHasFixedSize(true);
                                layoutManager = new LinearLayoutManager(Activity_Stats_Section.this);
                                recyclerView.setLayoutManager(layoutManager);

                                // Instantiate adapter and bind it with recylerview
                                statsSectionAdapter = new StatsSectionAdapter(sections);
                                recyclerView.setAdapter(statsSectionAdapter);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Activity_Stats_Section.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Activity_Stats_Section.this, "Failed..", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }

    public void setSections(JSONArray sections) {
        this.sections = sections;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_section);
        this.getSupportActionBar().hide();

        characterChoice = getIntent().getStringExtra("character_choice");

        getRankingJson();
    }
}
