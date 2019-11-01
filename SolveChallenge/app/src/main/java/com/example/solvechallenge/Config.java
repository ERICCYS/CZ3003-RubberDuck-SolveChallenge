package com.example.solvechallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;

public class Config {


//    public static final String baseUrl = "http://10.27.37.196:8082/api/";
    public static final String baseUrl = "http://206.189.151.97:8082/api/";

    public static final String[] characters = {"Lead Developer", "Product Manager", "Quality Manager",};

    public static final String[] worlds = {"Requirement Gathering and Analysis", "Design", "Implementation", "Testing and Deployment", "Maintenance"};

    public static final MediaType JSON = MediaType.parse("application/json");

//    public static final Map<String, Integer> worldCodes = new HashMap<String, Integer>(){
//        {
//            put("Requirement Gathering and Analysis", 0);
//            put("Design", 1);
//            put("Implementation", 2);
//            put("Testing and Deployment", 3);
//            put("Maintenance", 4);
//        }
//    };



    public static final ArrayList<ArrayList<String>> sections = new ArrayList<ArrayList<String>>() {
        {
            add(new ArrayList<String>(){
                {
                    add("Requirement Engineering");
                    add("Requirement Analysis");
                    add("Requirement Specification and Validations");
                }
            });
            add(new ArrayList<String>(){
                {
                    add("Design Phrase I");
                    add("Design Phrase II");
                    add("Design Phrase III");
                }
            });
            add(new ArrayList<String>(){
                {
                    add("Implementation FE Dev");
                    add("Implementation BE Dev");
                    add("Implementation ALL");
                }
            });
            add(new ArrayList<String>(){
                {
                    add("Testing");
                    add("Deployment");
                    add("DevOps");
                }
            });
            add(new ArrayList<String>(){
                {
                    add("Maintenance I");
                    add("Maintenance II");
                    add("Maintenance III");
                }
            });
        }
    };



    public static final String[]levels = {"Easy", "Medium", "Hard"};


    public static String getBaseUrl() {
        return baseUrl;
    }

    public static String[] getCharacters() {
        return characters;
    }

    public static String[] getWorlds() {
        return worlds;
    }


//    public static Map<String, Integer> getWorldCodes() {
//        return worldCodes;
//    }

//    public static List<Map<String, Integer>> getSectionCodes() {
//        return sectionCodes;
//    }

    public static ArrayList<ArrayList<String>> getSections() {
        return sections;
    }

    public static String[] getLevels() {
        return levels;
    }
}
