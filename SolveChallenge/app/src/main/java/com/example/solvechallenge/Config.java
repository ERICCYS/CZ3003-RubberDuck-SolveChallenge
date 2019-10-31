package com.example.solvechallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Config {


    public static final String baseUrl = "http://10.27.185.112:8082/api/";
//    public static final String baseUrl = "http://206.189.151.97:8082/api/";

    public static final String[] characters = {"Product Manager", "Quality Manager", "Lead Developer"};

    public static final String[] worlds = {"Requirement Gathering and Analysis", "Design", "Implementation", "Testing and Deployment", "Maintenance"};

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



//        add(new ArrayList<String>(){Arrays.asList("Requirement Engineering","Requirement Analysis","Requirement Specification and Validations")};);
//    public static final List<Map<String, Integer>> sectionCodes = new ArrayList<Map<String, Integer>>(){
//        {
//            add(new HashMap<String, Integer>() {{
//                put("Requirement Engineering", 0);
//                put("Requirement Analysis", 1);
//                put("Requirement Specification and Validations", 2);
//            }});
//            add(new HashMap<String, Integer>() {{
//                put("Design Phrase I", 0);
//                put("Design Phrase II", 1);
//                put("Design Phrase III", 2);
//            }});
//            add(new HashMap<String, Integer>() {{
//                put("Implementation FE Dev", 0);
//                put("Implementation BE Dev", 1);
//                put("Implementation ALL", 2);
//            }});
//            add(new HashMap<String, Integer>() {{
//                put("Testing", 0);
//                put("Deployment", 1);
//                put("DevOps", 2);
//            }});
//            add(new HashMap<String, Integer>() {{
//                put("Maintenance I", 0);
//                put("Maintenance II", 1);
//                put("Maintenance III", 2);
//            }});
//        }
//    };

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
