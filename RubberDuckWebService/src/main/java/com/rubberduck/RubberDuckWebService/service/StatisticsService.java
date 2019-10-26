package com.rubberduck.RubberDuckWebService.service;

import javafx.util.Pair;

import java.util.List;

public interface StatisticsService {
    // each question, count the average score get for that question, how many student have attempted
    List<Object> getQuestionPerformance(String character);

    // each section, count the average score of student get in this section, how many student have attempted
    List<Object> getSectionPerformance(String character, List<Pair<String, String>> worldSections);

    // each world, count the average score of student get in this section, how many student have attempted
    List<Object> getWorldPerformance(String character, List<String> world);
}
