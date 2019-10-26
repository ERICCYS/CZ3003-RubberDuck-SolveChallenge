package com.rubberduck.RubberDuckWebService.controller;

import com.rubberduck.RubberDuckWebService.JSONConvert;
import com.rubberduck.RubberDuckWebService.service.StatisticsService;
import com.rubberduck.RubberDuckWebService.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class StatisticsController {

    @Autowired
    StatusService statusService;

    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/statistic/question")
    public String getQuestionsStatistics(
            @RequestParam String character
    ) {
        return JSONConvert.JSONConverter(statisticsService.getQuestionPerformance(character));
    }

    @GetMapping("/statistic/section")
    public String getSectionStatistics(
            @RequestParam String character
    ) {
        Map<String, List<String>> worldSections = statusService.getWorldAndSection();
        return JSONConvert.JSONConverter(statisticsService.getSectionPerformance(character, worldSections));
    }

    @GetMapping("/statistic/world")
    public String getWorldStatistics(
            @RequestParam String character
    ) {
        List<String> worlds = statusService.getWorlds();
        return JSONConvert.JSONConverter(statisticsService.getWorldPerformance(character, worlds));
    }
}
