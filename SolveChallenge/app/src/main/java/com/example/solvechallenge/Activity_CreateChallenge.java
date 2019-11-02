package com.example.solvechallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Activity_CreateChallenge extends AppCompatActivity {

    Spinner createChallengeRoleSpinner;
    String character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__create_challenge);

        createChallengeRoleSpinner = (Spinner) findViewById(R.id.create_chall_char_sel_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.characters, R.layout.char_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        createChallengeRoleSpinner.setAdapter(adapter);
        createChallengeRoleSpinner.setOnItemSelectedListener(new spinnerListener());
    }

    class spinnerListener implements AdapterView.OnItemSelectedListener {


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            character = parent.getItemAtPosition(position).toString();
            System.out.println("Character selected: " + character);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(getApplicationContext(), "Please select a character.", Toast.LENGTH_SHORT).show();
        }
    }
}
