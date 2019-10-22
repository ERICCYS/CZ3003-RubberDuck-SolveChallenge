package com.example.solvechallenge;

import android.content.Intent;
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

public class Activity_SignIn extends AppCompatActivity {

    EditText name_box, email_box, password_box;
    Button password_show, start_button;
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

        name_box = findViewById(R.id.name_box);
        email_box = findViewById(R.id.email_box);
        password_box = (EditText) findViewById(R.id.password_box);
        password_show.setOnClickListener(new showButtonListener());

        //set up user role spinner
        user_role_spinner = (Spinner) findViewById(R.id.user_role_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.user_roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        user_role_spinner.setAdapter(adapter);
        user_role_spinner.setOnItemSelectedListener(new spinnerListener());

        //Once the user enter the game, validate user info
        start_button = (Button) findViewById(R.id.start_button);
        start_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String name = name_box.getText().toString();
                String email = email_box.getText().toString();
                String password = password_box.getText().toString();

                if(name.equals("") || email.equals("") || password.equals("")){
                    Toast.makeText(LoginPage.this, "Please Enter the Details", Toast.LENGTH_SHORT).show();
                }else{
                    boolean login_successful = findUser(name, email, password);
                    if(login_successful){
                        if(user_role.equals("Staff")){
                            // start next staff activity
                            //TODO
                        }else if(user_role.equals("Student")){
                            //start next student activity
                            //TODO
                        }
                    }else{
                        Toast.makeText(LoginPage.this, "Sorry, user not found.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    public boolean findUser(String name, String email, String password){
        // consult backend
        //TODO
        return true;
    }

    class showButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(password_show.getText().toString().equals("SHOW")){
                password_box.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                password_box.setText("HIDE");
            }else{
                password_box.setTransformationMethod(PasswordTransformationMethod.getInstance());
                password_show.setText("SHOW");
            }
        }
    }

    class spinnerListener implements AdapterView.OnItemSelectedListener{


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




