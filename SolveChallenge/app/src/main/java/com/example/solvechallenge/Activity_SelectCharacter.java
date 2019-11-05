package com.example.solvechallenge;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.solvechallenge.ui.SelectCharacter.SectionsPagerAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Activity_SelectCharacter extends AppCompatActivity {

    String[] characters = Config.getCharacters();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_character);
        final SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        final ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tab_characSelect);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab_start_characSelect);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String characterChosen = characters[viewPager.getCurrentItem()];

                OkHttpClient client = new OkHttpClient();
                String url = Config.baseUrl + "world/initialize";
                HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
                httpBuilder.addQueryParameter("character", characterChosen);
                httpBuilder.addQueryParameter("studentId", App_Data.getUserId().toString());

                Request request = new Request.Builder()
                        .url(httpBuilder.build())
                        .addHeader("Authorization", App_Data.getAccessToken())
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Activity_SelectCharacter.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Activity_SelectCharacter.this, "Failed...", Toast.LENGTH_SHORT).show();
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
                                App_Data.setCharacter(characterChosen);
                                App_Data.setWorld_upperbound(Integer.parseInt(r.get("world").toString()));
                                App_Data.setSection_upperbound(Integer.parseInt(r.get("section").toString()));
                                App_Data.setLevel_upperbound(Integer.parseInt(r.get("level").toString()));

                            }catch (JSONException e) {
                                e.printStackTrace();
                            }

                            App_Data.printAllData();

                            Intent myIntent = new Intent(Activity_SelectCharacter.this, Activity_World.class);
                            startActivity(myIntent);

                        } else {
                            Activity_SelectCharacter.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Activity_SelectCharacter.this, "Failed..", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });

            }
        });
    }
}