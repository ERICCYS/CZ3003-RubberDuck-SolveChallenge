package com.example.solvechallenge.Leaderboard;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.solvechallenge.R;
import com.example.solvechallenge.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Activity_Leaderboard extends AppCompatActivity {

    private JSONArray students;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter leaderboardAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private void getRankingJson() {

        OkHttpClient client = new OkHttpClient();
        String url = Config.baseUrl + "leaderboard";
        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();

        Request request = new Request.Builder()
                .url(httpBuilder.build())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Activity_Leaderboard.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Activity_Leaderboard.this, "Failed...", Toast.LENGTH_SHORT).show();
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
                        setStudents(r);

                        Activity_Leaderboard.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Set up recylcerview
                                recyclerView = findViewById(R.id.view_recycler_leaderboard);
                                recyclerView.setHasFixedSize(true);
                                layoutManager = new LinearLayoutManager(Activity_Leaderboard.this);
                                recyclerView.setLayoutManager(layoutManager);

                                // Instantiate adapter and bind it with recylerview
                                leaderboardAdapter = new LeaderboardAdapter(students, Activity_Leaderboard.this,Activity_Leaderboard.this);
                                recyclerView.setAdapter(leaderboardAdapter);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Activity_Leaderboard.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Activity_Leaderboard.this, "Failed..", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }

    public void setStudents(JSONArray students) {
        this.students = students;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        this.getSupportActionBar().hide();
        //get student ranking
        getRankingJson();
    }


}
