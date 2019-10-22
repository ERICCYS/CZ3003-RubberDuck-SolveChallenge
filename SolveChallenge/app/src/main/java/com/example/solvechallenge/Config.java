package com.example.solvechallenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {

    public static final String baseUrl = "http://10.27.103.141:8081/api/";

    public static final String[] characters = {"Product Manager", "Quality Manager", "Lead Developer"};

    public static final String[] worlds = {"Requirement Gathering and Analysis", "Design", "Implementation", "Testing and Deployment", "Maintenance"};

    public static final Map<String, Integer> worldCodes = new HashMap<String, Integer>(){
        {
            put("Requirement Gathering and Analysis", 0);
            put("Design", 1);
            put("Implementation", 2);
            put("Testing and Deployment", 3);
            put("Maintenance", 4);
        }
    };

    public static final List<Map<String, Integer>> sectionCodes = new ArrayList<Map<String, Integer>>(){
        {
            add(new HashMap<String, Integer>() {{
                put("Requirement Engineering", 0);
                put("Requirement Analysis", 1);
                put("Requirement Specification and Validations", 2);
            }});
            add(new HashMap<String, Integer>() {{
                put("Design Phrase I", 0);
                put("Design Phrase II", 1);
                put("Design Phrase III", 2);
            }});
            add(new HashMap<String, Integer>() {{
                put("Implementation FE Dev", 0);
                put("Implementation BE Dev", 1);
                put("Implementation ALL", 2);
            }});
            add(new HashMap<String, Integer>() {{
                put("Testing", 0);
                put("Deployment", 1);
                put("DevOps", 2);
            }});
            add(new HashMap<String, Integer>() {{
                put("Maintenance I", 0);
                put("Maintenance II", 1);
                put("Maintenance III", 2);
            }});
        }
    };

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static String[] getCharacters() {
        return characters;
    }

    public static String[] getWorlds() {
        return worlds;
    }

    public static Map<String, Integer> getWorldCodes() {
        return worldCodes;
    }

    public static List<Map<String, Integer>> getSectionCodes() {
        return sectionCodes;
    }
}
