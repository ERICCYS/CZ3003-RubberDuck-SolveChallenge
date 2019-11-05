package com.example.solvechallenge;

import android.app.ListActivity;
import android.widget.ArrayAdapter;
import android.os.AsyncTask;
import android.widget.ListView;
import java.util.ArrayList;

public class TwitterAsyncTask extends AsyncTask<Object, Void, ArrayList<String>> {
    ListActivity callerActivity;

    final static String TWITTER_API_KEY = "4txYjrr6kDLGUQWg8w6PNnxNP";
    final static String TWITTER_API_SECRET = "VvZlCx4dWwnBGGXFDkJRVrAQrZgipAlJpdGNw5d8LJt5OhaD41";

    @Override
    protected ArrayList<String> doInBackground(Object... params) {
        ArrayList<TwitterTweet> twitterTweets = null;
        ArrayList<String> tweets = new ArrayList<String>();
        callerActivity = (ListActivity) params[1];
        if (params.length > 0) {
            TwitterAPI twitterAPI = new TwitterAPI(TWITTER_API_KEY,TWITTER_API_SECRET);
            twitterTweets = twitterAPI.getTwitterTweets(params[0].toString());
            for(int i = 0; i<twitterTweets.size(); i++){
                String qn_stud = twitterTweets.get(i).toString();
                if(qn_stud.contains(App_Data.getUserName())) {
                    String tweet_qn = qn_stud.split(",")[0];
                    System.out.println(tweet_qn);
                    tweets.add(tweet_qn);
                }
            }
        }
        return tweets;
    }

    @Override
    protected void onPostExecute(ArrayList<String> tweets) {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(callerActivity, R.layout.twitter_tweets_list,
                        R.id.listTextView, tweets);
        callerActivity.setListAdapter(adapter);
        ListView lv = callerActivity.getListView();
        lv.setDividerHeight(0);
        //lv.setDivider(this.getResources().getDrawable(android.R.color.transparent));
        lv.setBackgroundColor(callerActivity.getResources().getColor(R.color.colorPrimary));
    }
}