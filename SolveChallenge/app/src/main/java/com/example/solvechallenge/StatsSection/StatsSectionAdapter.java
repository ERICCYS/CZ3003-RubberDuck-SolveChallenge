package com.example.solvechallenge.StatsSection;

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

class StatsSectionAdapter extends RecyclerView.Adapter<StatsSectionAdapter.StatsSectionViewHolder>{

    private JSONArray sections;

    public StatsSectionAdapter(JSONArray sections){
        this.sections = sections;
    }

    @NonNull
    @Override
    public StatsSectionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_stats, viewGroup, false);
        return new StatsSectionViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull StatsSectionViewHolder holder, int position){

        try {
            JSONObject section = sections.getJSONObject(position);
            String title = section.get("section").toString();
            String attempt = section.get("totalAttempts").toString();
            String score = section.get("averageScore").toString();
            holder.title.setText(title);
            holder.attempts.setText(attempt);
            holder.score.setText(score);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return sections.length();
    }

    public static class StatsSectionViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public TextView score;
        public TextView attempts;

        // Prepare the cardview for each user
        public StatsSectionViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.txtview_title_stats);
            score = itemView.findViewById(R.id.txtview_score_stats_world);
            attempts = itemView.findViewById(R.id.txtview_attempt_stats_world);
        }


    }
}