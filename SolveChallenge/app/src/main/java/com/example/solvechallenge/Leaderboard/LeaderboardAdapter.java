package com.example.solvechallenge.Leaderboard;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.solvechallenge.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder>{

    private JSONArray students;

    public LeaderboardAdapter(JSONArray students){
        this.students = students;
    }

    @NonNull
    @Override
    public LeaderboardAdapter.LeaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_leaderboard, viewGroup, false);
        return new LeaderboardViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull LeaderboardViewHolder holder, int position){



        try {

            JSONObject studnet = students.getJSONObject(position);
            String name = studnet.get("userName").toString();
            String score = studnet.get("mark").toString();


            holder.name.setText(name);
            holder.score.setText(score);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return students.length();
    }

    public static class LeaderboardViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView score;
        public Button challenge;

        // Prepare the cardview for each user
        public LeaderboardViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtview_name_leaderboard);
            score = itemView.findViewById(R.id.txtview_score_leaderboard);
            challenge = itemView.findViewById(R.id.btn_challenge_leaderboard);

            challenge.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int position = getAdapterPosition();

                    //start next activity
                }
            });

        }


    }
}