package com.rubberduck.RubberDuckWebService.controller;

import com.rubberduck.RubberDuckWebService.JSONConvert;
import com.rubberduck.RubberDuckWebService.service.StatisticsService;
import com.rubberduck.RubberDuckWebService.service.StatusService;
import com.rubberduck.RubberDuckWebService.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class StatisticsController {

    @Autowired
    StatusService statusService;

    @Autowired
    StatisticsService statisticsService;

    @Autowired
    ValidationService validationService;

    @GetMapping("/statistic/question")
    public String getQuestionsStatistics(
            @RequestParam String character,
            @RequestHeader(value = "Authorization") String accessToken
    ) {
        if (Long.parseLong(validationService.getUserId(accessToken, "TEACHER")) > 0) {
            return JSONConvert.JSONConverter(statisticsService.getQuestionPerformance(character));

        } else {
            throw new IllegalArgumentException();
        }
    }

    @GetMapping("/statistic/section")
    public String getSectionStatistics(
            @RequestParam String character,
            @RequestHeader(value = "Authorization") String accessToken
    ) {
        if (Long.parseLong(validationService.getUserId(accessToken, "TEACHER")) > 0) {
            Map<String, List<String>> worldSections = statusService.getWorldAndSection();
            return JSONConvert.JSONConverter(statisticsService.getSectionPerformance(character, worldSections));
        } else {
            throw new IllegalArgumentException();
        }
    }

    @GetMapping("/statistic/world")
    public String getWorldStatistics(
            @RequestParam String character,
            @RequestHeader(value = "Authorization") String accessToken
    ) {
        if (Long.parseLong(validationService.getUserId(accessToken, "TEACHER")) > 0) {
            List<String> worlds = statusService.getWorlds();
            return JSONConvert.JSONConverter(statisticsService.getWorldPerformance(character, worlds));
        } else {
            throw new IllegalArgumentException();
        }

    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Not authorized to view the statistic")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badAuthenticationException() {

    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Data not found")
    @ExceptionHandler(NullPointerException.class)
    public void notFoundException() {

    }
}
