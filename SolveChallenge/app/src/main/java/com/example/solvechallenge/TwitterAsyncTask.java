package com.example.solvechallenge;

import android.app.ListActivity;
import android.widget.ArrayAdapter;
import android.os.AsyncTask;
import android.widget.ListView;
import java.util.ArrayList;

public class TwitterAsyncTask extends AsyncTask<Object, Void, ArrayList<com.example.solvechallenge.TwitterTweet>> {
    ListActivity callerActivity;

    final static String TWITTER_API_KEY = "NbmuS6DTsAKGnhIv95yuCBXVn";
    final static String TWITTER_API_SECRET = "JOOUmKuC424xWad8hJnTL41yA9L2hhSq48KAEEhwzy7XMkqfLQ";

    @Override
    protected ArrayList<com.example.solvechallenge.TwitterTweet> doInBackground(Object... params) {
        ArrayList<com.example.solvechallenge.TwitterTweet> twitterTweets = null;
        callerActivity = (ListActivity) params[1];
        if (params.length > 0) {
            TwitterAPI twitterAPI = new TwitterAPI(TWITTER_API_KEY,TWITTER_API_SECRET);
            twitterTweets = twitterAPI.getTwitterTweets(params[0].toString());
        }
        return twitterTweets;
    }

    @Override
    protected void onPostExecute(ArrayList<com.example.solvechallenge.TwitterTweet> twitterTweets) {
        ArrayAdapter<com.example.solvechallenge.TwitterTweet> adapter =
                new ArrayAdapter<com.example.solvechallenge.TwitterTweet>(callerActivity, R.layout.twitter_tweets_list,
                        R.id.listTextView, twitterTweets);
        callerActivity.setListAdapter(adapter);
        ListView lv = callerActivity.getListView();
        lv.setDividerHeight(0);
        //lv.setDivider(this.getResources().getDrawable(android.R.color.transparent));
        lv.setBackgroundColor(callerActivity.getResources().getColor(R.color.colorPrimary));
    }
}