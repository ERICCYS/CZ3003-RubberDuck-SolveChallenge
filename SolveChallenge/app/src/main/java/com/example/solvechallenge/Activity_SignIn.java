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

    EditText editText_email_signin, editText_password_signin;
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


        editText_email_signin = findViewById(R.id.editText_email_signin);
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
        btn_start_signin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String email = editText_email_signin.getText().toString();
                String password = editText_password_signin.getText().toString();

                if(email.equals("") || password.equals("")){
                    Toast.makeText(Activity_SignIn.this, "Please Enter the Details", Toast.LENGTH_SHORT).show();
                }else{
                    boolean login_successful = findUser(user_role, email, password);
                    if(login_successful){
                        if(user_role.equals("Staff")){
                            // start next staff activity
                            //TODO
                        }else if(user_role.equals("Student")){
                            //start next student activity
                            //TODO
                            Intent intent = new Intent(Activity_SignIn.this, Activity_SelectCharacter.class);
                            startActivity(intent);
                        }
                    }else{
                        Toast.makeText(Activity_SignIn.this, "Sorry, user not found.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    public boolean findUser(String user_role, String email, String password){
        // consult backend
        //TODO
        return true;
    }

    class showButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(btn_passwordShow_signin.getText().toString().equals("SHOW")){
                editText_password_signin.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                btn_passwordShow_signin.setText("HIDE");
            }else{
                editText_password_signin.setTransformationMethod(PasswordTransformationMethod.getInstance());
                btn_passwordShow_signin.setText("SHOW");
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




