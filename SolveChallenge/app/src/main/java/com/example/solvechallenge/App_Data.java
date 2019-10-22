package com.example.solvechallenge;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class App_Data {

    public static String baseUrl = "http://10.27.103.141:8081/api/";

    public static Long userId;
    public static String accessToken;

    public static String character;
    public static int world;
    public static int section;
    public static int level;

    public static int no_question;

    public static int world_upperbound;
    public static int section_upperbound;
    public static int level_upperbound;

    public static Long getUserId() {
        return userId;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static String getCharacter() {
        return character;
    }

    public static int getWorld() {
        return world;
    }

    public static int getSection() {
        return section;
    }

    public static int getLevel() {
        return level;
    }

    public static int getNo_question() {
        return no_question;
    }

    public static int getWorld_upperbound() {
        return world_upperbound;
    }

    public static int getSection_upperbound() {
        return section_upperbound;
    }

    public static int getLevel_upperbound() {
        return level_upperbound;
    }

    public static void setAccessToken(String accessToken) {
        App_Data.accessToken = accessToken;
    }

    public static void setCharacter(String character) {
        App_Data.character = character;
    }

    public static void setWorld(int world) {
        App_Data.world = world;
    }

    public static void setSection(int section) {
        App_Data.section = section;
    }

    public static void setLevel(int level) {
        App_Data.level = level;
    }

    public static void setNo_question(int no_question) {
        App_Data.no_question = no_question;
    }

    public static void setUserId(Long userId) { App_Data.userId = userId; }

    public static void setWorld_upperbound(int world_upperbound) { App_Data.world_upperbound = world_upperbound; }

    public static void setSection_upperbound(int section_upperbound) { App_Data.section_upperbound = section_upperbound; }

    public static void setLevel_upperbound(int level_upperbound) { App_Data.level_upperbound = level_upperbound; }

    public static void printAllData(){
        System.out.println(App_Data.userId);
        System.out.println(App_Data.accessToken);
        System.out.println(App_Data.character);
        System.out.println(App_Data.world);
        System.out.println(App_Data.world_upperbound);
        System.out.println(App_Data.section);
        System.out.println(App_Data.section_upperbound);
        System.out.println(App_Data.level);
        System.out.println(App_Data.level_upperbound);
        System.out.println(App_Data.no_question);
    }



}
