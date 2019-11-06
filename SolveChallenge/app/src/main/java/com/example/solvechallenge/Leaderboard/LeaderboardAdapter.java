package com.example.solvechallenge.Leaderboard;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.solvechallenge.Activity_Challenge;
import com.example.solvechallenge.App_Data;
import com.example.solvechallenge.Config;
import com.example.solvechallenge.R;
import com.example.solvechallenge.ui.SelectCharacter.Activity_Section;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder>{

    private static JSONObject challenge;
    private JSONArray students;
    private static Context context;
    private static Dialog myDialog;
    private static Activity activity;

    public LeaderboardAdapter(JSONArray students, Activity activity, Context context){
        this.activity = activity;
        this.students = students;
        this.context = context;
        this.myDialog = new Dialog(context);
    }

    public static Context getContext() {
        return context;
    }

    public static JSONObject getChallenge() {
        return challenge;
    }

    public static void setChallenge(JSONObject challenge) {
        LeaderboardAdapter.challenge = challenge;
    }

    @NonNull
    @Override
    public LeaderboardAdapter.LeaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_leaderboard, viewGroup, false);
        return new LeaderboardViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull LeaderboardViewHolder holder, final int position){
        try {

            final JSONObject studnet = students.getJSONObject(position);
            final String name = studnet.get("userName").toString();
            String score = studnet.get("mark").toString();

            holder.name.setText(name);
            holder.score.setText(score);

            if (name.equals(App_Data.getUserName())){
//                Toast.makeText(context, "Not yourself ... ", Toast.LENGTH_SHORT).show();
                System.out.println("cant challenge yourself");
            }
            else {
                holder.challenge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // Get challenge data
                        OkHttpClient client = new OkHttpClient();
                        String url = Config.baseUrl + "challenge/username";
                        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
                        httpBuilder.addQueryParameter("userName", name);
                        Request request = new Request.Builder().url(httpBuilder.build()).build();

                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        System.out.println("#####################");
                                        System.out.println("Failed");
                                        System.out.println("#####################");
                                        Toast.makeText(context, "Request failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                e.printStackTrace();
                            }

                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                System.out.println(response);
                                if (response.isSuccessful()) {
                                    try {
                                        final JSONObject this_challenge = new JSONObject(response.body().string());
                                        setChallenge(this_challenge);

                                        activity.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                TextView txtclose;
                                                TextView txtUsername;
                                                TextView txtPass;
                                                TextView txtFail;
                                                TextView txtQuestion;
                                                Button btnTakeChallenge;

                                                myDialog.setContentView(R.layout.challenge_info_popup);
                                                txtclose = (TextView) myDialog.findViewById(R.id.txtclose_challenge_info_popup);
                                                txtUsername = (TextView) myDialog.findViewById(R.id.userid_challenge_info_popup);
                                                txtQuestion = (TextView) myDialog.findViewById(R.id.noOfQuestions_challenge_info_popup);
                                                txtPass = (TextView) myDialog.findViewById(R.id.noOfPass_challenge_info_popup);
                                                txtFail = (TextView) myDialog.findViewById(R.id.noOfFail_challenge_info_popup);
                                                btnTakeChallenge = (Button) myDialog.findViewById(R.id.takeChallenge_challenge_info_popup);

                                                txtUsername.setText(name + "'s Challenge");
                                                try {
                                                    Integer questionCount = ((JSONArray)getChallenge().get("questionIds")).length();

//                                                    ArrayList<Integer> questionNumbers = (ArrayList<Integer>) ;
                                                    txtQuestion.setText(questionCount.toString());
                                                    txtFail.setText(getChallenge().get("failureCount").toString());
                                                    txtPass.setText(getChallenge().get("successCount").toString());
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }

                                                txtclose.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        myDialog.dismiss();
                                                    }
                                                });
                                                btnTakeChallenge.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        try {
                                                            JSONArray questionIdObjects = (JSONArray) challenge.get("questionIds");
                                                            ArrayList<Integer> questionIds = new ArrayList<>();
                                                            for (int i = 0; i < questionIdObjects.length(); i++) {
                                                                Integer questionId = (Integer) questionIdObjects.get(i);
                                                                questionIds.add(questionId);
                                                            }

                                                            Intent intent = new Intent(activity, Activity_Challenge.class);
                                                            intent.putIntegerArrayListExtra("question_ids", questionIds);
                                                            intent.putExtra("challenge_id", getChallenge().get("id").toString());
                                                            context.startActivity(intent);
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                });
                                                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                                myDialog.show();
                                            }
                                        });
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                } else {
                                    activity.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            System.out.println("#####################");
                                            System.out.println("not successful");
                                            System.out.println("#####################");
                                            Toast.makeText(context, "No challenge created by this user ", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        });
                    }
                });
            }


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


        }


    }
}