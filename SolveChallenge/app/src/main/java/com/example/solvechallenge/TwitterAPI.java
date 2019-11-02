package com.example.solvechallenge;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class TwitterAPI {

    private String twitterApiKey;
    private String twitterAPISecret;
    final static String TWITTER_TOKEN_URL = "https://api.twitter.com/oauth2/token";
    final static String TWITTER_STREAM_URL = "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=";

    public TwitterAPI(String twitterAPIKey, String twitterApiSecret){
        this.twitterApiKey = twitterAPIKey;
        this.twitterAPISecret = twitterApiSecret;
    }

    public ArrayList<com.example.solvechallenge.TwitterTweet> getTwitterTweets(String screenName) {
        ArrayList<com.example.solvechallenge.TwitterTweet> twitterTweetArrayList = null;
        try {
            String twitterUrlApiKey = URLEncoder.encode(twitterApiKey, "UTF-8");
            String twitterUrlApiSecret = URLEncoder.encode(twitterAPISecret, "UTF-8");
            String twitterKeySecret = twitterUrlApiKey + ":" + twitterUrlApiSecret;
            String twitterKeyBase64 = Base64.encodeToString(twitterKeySecret.getBytes(), Base64.NO_WRAP);
            TwitterAuthToken twitterAuthToken = getTwitterAuthToken(twitterKeyBase64);
            twitterTweetArrayList = getTwitterTweets(screenName, twitterAuthToken);
        } catch (UnsupportedEncodingException ex) {
        } catch (IllegalStateException ex1) {
        }
        return twitterTweetArrayList;
    }

    public ArrayList<com.example.solvechallenge.TwitterTweet> getTwitterTweets(String screenName,
                                                                          TwitterAuthToken twitterAuthToken) {
        ArrayList<com.example.solvechallenge.TwitterTweet> twitterTweetArrayList = null;
        if (twitterAuthToken != null && twitterAuthToken.token_type.equals("bearer")) {
            HttpGet httpGet = new HttpGet(TWITTER_STREAM_URL + screenName);
            httpGet.setHeader("Authorization", "Bearer " + twitterAuthToken.access_token);
            httpGet.setHeader("Content-Type", "application/json");
            String twitterTweets = com.example.solvechallenge.util.getHttpResponse(httpGet);
            twitterTweetArrayList = convertJsonToTwitterTweet(twitterTweets);
        }
        return twitterTweetArrayList;
    }

    public TwitterAuthToken getTwitterAuthToken(String twitterKeyBase64) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(TWITTER_TOKEN_URL);
        httpPost.setHeader("Authorization", "Basic " + twitterKeyBase64);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        httpPost.setEntity(new StringEntity("grant_type=client_credentials"));
        String twitterJsonResponse = com.example.solvechallenge.util.getHttpResponse(httpPost);
        return convertJsonToTwitterAuthToken(twitterJsonResponse);
    }

    private TwitterAuthToken convertJsonToTwitterAuthToken(String jsonAuth) {
        TwitterAuthToken twitterAuthToken = null;
        if (jsonAuth != null && jsonAuth.length() > 0) {
            try {
                Gson gson = new Gson();
                twitterAuthToken = gson.fromJson(jsonAuth, TwitterAuthToken.class);
            } catch (IllegalStateException ex) { }
        }
        return twitterAuthToken;
    }

    private ArrayList<com.example.solvechallenge.TwitterTweet> convertJsonToTwitterTweet(String twitterTweets) {
        ArrayList<com.example.solvechallenge.TwitterTweet> twitterTweetArrayList = null;
        if (twitterTweets != null && twitterTweets.length() > 0) {
            try {
                Gson gson = new Gson();
                twitterTweetArrayList =
                        gson.fromJson(twitterTweets, new TypeToken<ArrayList<com.example.solvechallenge.TwitterTweet>>(){}.getType());
            } catch (IllegalStateException e) {
            }
        }
        return twitterTweetArrayList;
    }
    private class TwitterAuthToken {
        String token_type;
        String access_token;
    }
}