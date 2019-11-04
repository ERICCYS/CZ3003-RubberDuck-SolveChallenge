package com.example.solvechallenge;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Activity_CreateChallenge extends AppCompatActivity {

    String noOfQuestionOfworld0, noOfQuestionOfworld1, noOfQuestionOfworld2, noOfQuestionOfworld3, noOfQuestionOfworld4;

    EditText questionsOfWorld0;
    EditText questionsOfWorld1;
    EditText questionsOfWorld2;
    EditText questionsOfWorld3;
    EditText questionsOfWorld4;
    Button confirmbtn;

    String[] world_names = Config.getWorlds();
    JSONArray worldsInfo = new JSONArray();

    public JSONArray getWorldsInfo() {
        return worldsInfo;
    }

    public void setWorldsInfo(JSONArray worldsInfo) {
        this.worldsInfo = worldsInfo;
    }

    public void addWorldInfo(final String world, final String count){
        try{

            if ((worldsInfo.length() != 0) && (world == world_names[0]) ) {worldsInfo = new JSONArray();}

            worldsInfo.put(new JSONObject(){{
                put("world", world);
                put("count", count);
            }});
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__create_challenge);

        questionsOfWorld0 = (EditText) findViewById(R.id.create_chall_world0_editText);
        questionsOfWorld1 = (EditText) findViewById(R.id.create_chall_world1_editText);
        questionsOfWorld2 = (EditText) findViewById(R.id.create_chall_world2_editText);
        questionsOfWorld3 = (EditText) findViewById(R.id.create_chall_world3_editText);
        questionsOfWorld4 = (EditText) findViewById(R.id.create_chall_world4_editText);
        confirmbtn = (Button) findViewById(R.id.btn_confirm_createChanllenge);

        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                noOfQuestionOfworld0 = questionsOfWorld0.getText().toString();
                noOfQuestionOfworld1 = questionsOfWorld1.getText().toString();
                noOfQuestionOfworld2 = questionsOfWorld2.getText().toString();
                noOfQuestionOfworld3 = questionsOfWorld3.getText().toString();
                noOfQuestionOfworld4 = questionsOfWorld4.getText().toString();
                String[] noOfQuestionOfworlds={noOfQuestionOfworld0, noOfQuestionOfworld1, noOfQuestionOfworld2, noOfQuestionOfworld3, noOfQuestionOfworld4};

                OkHttpClient client = new OkHttpClient();
                String url = Config.baseUrl + "challenge";
                HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();

                JSONObject newChallenge = new JSONObject();
                try {
                    newChallenge.put("creatorId", App_Data.getUserId());
                    newChallenge.put("character", App_Data.getCharacter());


                    for (int i=0; i<5; i++){
                        System.out.println(world_names[i] + "  " + noOfQuestionOfworlds[i]);
                        System.out.println(noOfQuestionOfworlds[i].getClass());
                        if (noOfQuestionOfworlds[i].equals("")) {
                            continue;
                        }
                        addWorldInfo(world_names[i], noOfQuestionOfworlds[i]);
                    }


                    newChallenge.put("worldQuestion", getWorldsInfo());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RequestBody body = RequestBody.create(Config.JSON, newChallenge.toString());

                System.out.println(newChallenge);

                Request request = new Request.Builder()
                        .url(httpBuilder.build())
                        .addHeader("Authorization", App_Data.getAccessToken())
                        .post(body)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Activity_CreateChallenge.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Activity_CreateChallenge.this, "Failed...", Toast.LENGTH_SHORT).show();
                            }
                        });
                        e.printStackTrace();
                    }

                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        System.out.println(response);
                        if (response.isSuccessful()) {
                            Activity_CreateChallenge.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Activity_CreateChallenge.this, "Successfully set challenges", Toast.LENGTH_SHORT).show();
                                    App_Data.printAllData();
                                    Intent myIntent = new Intent(Activity_CreateChallenge.this, Activity_World.class);
                                    startActivity(myIntent);
                                }
                            });


                        } else {
                            Activity_CreateChallenge.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Activity_CreateChallenge.this, "Failed..........", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });


            }
        });

    }

}
