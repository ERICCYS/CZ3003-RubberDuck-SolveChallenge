package com.rubberduck.RubberDuckWebService.controller;

import com.rubberduck.RubberDuckWebService.JSONConvert;
import com.rubberduck.RubberDuckWebService.model.Question;
import com.rubberduck.RubberDuckWebService.service.QuestionService;
import com.rubberduck.RubberDuckWebService.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    ValidationService validationService;

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

    @PostMapping("/question")
    public String addQuestion(
            @RequestHeader(value = "Authorization") String accessToken,
            @Valid @RequestBody Question question
    ) {
        if (Long.parseLong(validationService.getUserId(accessToken, "TEACHER")) > 0) {
            return JSONConvert.JSONConverter(questionService.save(question));
        } else {
            throw new IllegalArgumentException();
        }
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Unauthorized to post question")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badAuthenticationException() {

    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Question doesn't exist")
    @ExceptionHandler(NullPointerException.class)
    public void notFoundException() {

    }
}
