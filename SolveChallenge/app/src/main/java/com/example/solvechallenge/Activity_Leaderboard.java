package com.example.solvechallenge;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;

public class Activity_Leaderboard extends AppCompatActivity {

    private JSONArray students;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter leaderboardAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public JSONArray getStudents() {
        return students;
    }

    public void setStudents(JSONArray students) {
        this.students = students;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__leaderboard);

        // Set up recylcerview
        recyclerView = findViewById(R.id.view_recycler_leaderboard);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Instantiate adapter and bind it with recylerview

        leaderboardAdapter = new LeaderboardAdapter(students);
        recyclerView.setAdapter(leaderboardAdapter);
    }


}
