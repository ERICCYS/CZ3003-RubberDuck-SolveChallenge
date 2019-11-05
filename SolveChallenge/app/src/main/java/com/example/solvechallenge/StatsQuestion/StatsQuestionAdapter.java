package com.example.solvechallenge.StatsQuestion;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.solvechallenge.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class StatsQuestionAdapter extends RecyclerView.Adapter<StatsQuestionAdapter.StatsQuestionViewHolder>{

    private JSONArray questions;

    public StatsQuestionAdapter(JSONArray questions){
        this.questions = questions;
    }

    @NonNull
    @Override
    public StatsQuestionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_stats, viewGroup, false);
        return new StatsQuestionViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull StatsQuestionViewHolder holder, int position){

        try {
            //TODO: Get data from question JSONObject
            JSONObject question = questions.getJSONObject(position);
            String title = question.get("questionId").toString();
            String fullScore = question.get("questionFullScore").toString();
            String averageScore = question.get("questionAverageScore").toString();
            String attempts = question.get("questionAttempts").toString();

            holder.attempts.setText(attempts);
            holder.scoreName.setText("Average Score/Full Score");
            holder.score.setText(averageScore + "/" + fullScore);
            holder.title.setText("Question ID: " + title);

//
//            holder.name.setText(name);
//            holder.score.setText(score);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return questions.length();
    }

    public static class StatsQuestionViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public TextView score;
        public TextView attempts;
        public TextView scoreName;

        // Prepare the cardview for each user
        public StatsQuestionViewHolder(@NonNull View itemView) {
            super(itemView);

//            title = itemView.findViewById(R.id.txtview_title_stats);
//            score = itemView.findViewById(R.id.txtview_score_stats_world);
//            attempts = itemView.findViewById(R.id.txtview_attempt_stats_world);
//
            title = itemView.findViewById(R.id.txtview_title_stats);
            scoreName = itemView.findViewById(R.id.txtview_scoreName_stats);
            score = itemView.findViewById(R.id.txtview_score_stats_world);
            attempts = itemView.findViewById(R.id.txtview_attempt_stats_world);
        }


    }
}