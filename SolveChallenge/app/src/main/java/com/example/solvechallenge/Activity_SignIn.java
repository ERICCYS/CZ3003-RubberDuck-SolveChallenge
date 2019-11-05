package com.example.solvechallenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Activity_SignIn extends AppCompatActivity {

    EditText editText_username_signin, editText_password_signin;
    Button btn_passwordShow_signin, btn_start_signin;
    Spinner user_role_spinner;
    String user_role;

    public void switchToNextActivity(View view) {
        Intent intent = new Intent(this, Activity_SelectCharacter.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        this.getSupportActionBar().hide();


        editText_username_signin = findViewById(R.id.editText_username_signin);
        editText_password_signin = findViewById(R.id.editText_password_signin);
        btn_passwordShow_signin = findViewById(R.id.btn_passwordShow_signin);
        btn_passwordShow_signin.setOnClickListener(new showButtonListener());

        //set up user role spinner
        user_role_spinner = (Spinner) findViewById(R.id.user_role_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.user_roles, R.layout.role_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        user_role_spinner.setAdapter(adapter);
        user_role_spinner.setOnItemSelectedListener(new spinnerListener());

        //Once the user enter the game, validate user info
        btn_start_signin = (Button) findViewById(R.id.btn_start_signin);
        btn_start_signin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                final String username = editText_username_signin.getText().toString();
//                String password = editText_password_signin.getText().toString();

                final String username = "MAXI0008";
                String password = "123456";


                // get spinner value and decide which page to go

                if (user_role.equals("Teacher")) {

                    Intent myIntent = new Intent(Activity_SignIn.this, Activity_Stats_Main.class);
                    startActivity(myIntent);

                    OkHttpClient client = new OkHttpClient();
                    String url = Config.baseUrl + "teacher/signin";
                    HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
                    httpBuilder.addQueryParameter("userName", username);
                    httpBuilder.addQueryParameter("password", password);
                    Request request = new Request.Builder().url(httpBuilder.build()).build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Activity_SignIn.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Activity_SignIn.this, "Incorrect teacher account or password", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                try {
                                    JSONObject myResponse = new JSONObject(response.body().string());
                                    System.out.println(myResponse);
                                    App_Data.setAccessToken(myResponse.get("accessToken").toString());
                                    App_Data.setUserId(Long.parseLong(myResponse.get("userId").toString()));

                                    Intent myIntent = new Intent(Activity_SignIn.this, Activity_SelectCharacter.class);
                                    startActivity(myIntent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Activity_SignIn.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Activity_SignIn.this, "Incorrect teacher account or password", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });

                } else {
                    OkHttpClient client = new OkHttpClient();
                    String url = Config.baseUrl + "student/signin";
                    HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
                    httpBuilder.addQueryParameter("userName", username);
                    httpBuilder.addQueryParameter("password", password);
                    Request request = new Request.Builder().url(httpBuilder.build()).build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Activity_SignIn.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(Activity_SignIn.this, "Request failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                try {
                                    JSONObject myResponse = new JSONObject(response.body().string());
                                    System.out.println(myResponse);
                                    System.out.println(myResponse.get("accessToken").toString());
                                    System.out.println(Long.parseLong(myResponse.get("userId").toString()));
                                    App_Data.setAccessToken(myResponse.get("accessToken").toString());
                                    App_Data.setUserId(Long.parseLong(myResponse.get("userId").toString()));
                                    App_Data.setUserName(username);
                                    System.out.println(App_Data.getAccessToken());
                                    System.out.println(App_Data.getUserId());

                                    //Debugging Purpose
                                    Intent myIntent = new Intent(Activity_SignIn.this, Activity_SelectCharacter.class);
                                    startActivity(myIntent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Activity_SignIn.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Activity_SignIn.this, "Incorrect student account or password", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });

                }
            }
        });
    }


    class showButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (btn_passwordShow_signin.getText().toString().equals("SHOW")) {
                editText_password_signin.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                btn_passwordShow_signin.setText("HIDE");
            } else {
                editText_password_signin.setTransformationMethod(PasswordTransformationMethod.getInstance());
                btn_passwordShow_signin.setText("SHOW");
            }
        }
    }

    class spinnerListener implements AdapterView.OnItemSelectedListener {


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            user_role = parent.getItemAtPosition(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(getApplicationContext(), "Please select your role.", Toast.LENGTH_SHORT).show();
        }
    }

}




