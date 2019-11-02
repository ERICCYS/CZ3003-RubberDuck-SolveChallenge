package com.example.solvechallenge;

import com.google.gson.annotations.SerializedName;

public class TwitterTweet {

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("id")
    private String id;

    @SerializedName("text")
    private String text;

    public String getText() {
        return text;
    }
    @Override
    public String  toString(){
        return getText();
    }
}