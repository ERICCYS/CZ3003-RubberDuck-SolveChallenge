package com.example.solvechallenge.StatsWorld;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.solvechallenge.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class StatsWorldAdapter extends RecyclerView.Adapter<StatsWorldAdapter.StatsWorldViewHolder>{

    private JSONArray worlds;

    public StatsWorldAdapter(JSONArray students){
        this.worlds = students;
    }

    @NonNull
    @Override
    public StatsWorldAdapter.StatsWorldViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_stats, viewGroup, false);
        return new StatsWorldViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull StatsWorldViewHolder holder, int position){

        try {

            JSONObject world = worlds.getJSONObject(position);
            String title = world.get("world").toString();
            String attempts = world.get("totalAttempts").toString();
            String score = world.get("averageScore").toString();

            holder.attempts.setText(attempts);
            holder.score.setText(score);
            holder.title.setText(title);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return worlds.length();
    }

    public static class StatsWorldViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public TextView score;
        public TextView attempts;

        // Prepare the cardview for each user
        public StatsWorldViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txtview_title_stats);
            score = itemView.findViewById(R.id.txtview_score_stats_world);
            attempts = itemView.findViewById(R.id.txtview_attempt_stats_world);
        }


    }
}