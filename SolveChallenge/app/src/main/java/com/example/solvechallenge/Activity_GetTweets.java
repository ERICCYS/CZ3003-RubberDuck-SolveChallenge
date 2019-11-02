package com.example.solvechallenge;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;

public class Activity_GetTweets extends ListActivity {

    final static String twitterScreenName = "teacherassign";
//    final static String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (com.example.solvechallenge.util.isConnected(this)) {
            new com.example.solvechallenge.TwitterAsyncTask().execute(twitterScreenName,this);
        } else {
//            Log.v(TAG, "Network not Available!");
        }
    }
}
