package com.rubberduck.RubberDuckWebService.controller;

import com.rubberduck.RubberDuckWebService.JSONConvert;
import com.rubberduck.RubberDuckWebService.model.Question;
import com.rubberduck.RubberDuckWebService.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;

@RestController
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/questions")
    public String getAllQuestion() {
        List<Question> questions = questionService.findAll();
        return JSONConvert.JSONConverter(questions);
    }


    @GetMapping("/question")
    public String getQuestionById(
            @RequestParam Long id
    ) {
        Question question = questionService.findById(id);
        return JSONConvert.JSONConverter(question);
    }

    @GetMapping("/question/level")
    public String getQuestionByLevel(
            @RequestParam String level
    ) {
        List<Question> questions = questionService.findByLevel(level);
        return JSONConvert.JSONConverter(questions);
    }
}
