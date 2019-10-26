package com.rubberduck.RubberDuckWebService.service;

import java.util.List;
import java.util.Map;

public interface StatisticsService {
    // each question, count the average score get for that question, how many student have attempted
    List<Object> getQuestionPerformance(String character);

    // each section, count the average score of student get in this section, how many student have attempted
    List<Object> getSectionPerformance(String character, Map<String, List<String>> worldSections);

    // each world, count the average score of student get in this section, how many student have attempted
    List<Object> getWorldPerformance(String character, List<String> world);
}
