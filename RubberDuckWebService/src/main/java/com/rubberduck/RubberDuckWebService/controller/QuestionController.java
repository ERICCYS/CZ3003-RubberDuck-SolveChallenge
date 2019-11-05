package com.rubberduck.RubberDuckWebService.controller;

import com.rubberduck.RubberDuckWebService.JSONConvert;
import com.rubberduck.RubberDuckWebService.model.Question;
import com.rubberduck.RubberDuckWebService.service.QuestionService;
import com.rubberduck.RubberDuckWebService.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    ValidationService validationService;

    @PostMapping("/questions")
    public String getQuestionsById(
            @RequestBody List<Long> questionIds
    ) {
        List<Question> questions = new ArrayList<Question>();
        for (Long questionId : questionIds) {
            questions.add(questionService.findById(questionId));
        }
        return JSONConvert.JSONConverter(questions);
    }

    @GetMapping("/question")
    public String getQuestionById(
            @RequestParam Long id
    ) {
        Question question = questionService.findById(id);
        return JSONConvert.JSONConverter(question);
    }

    @GetMapping("/question/stage")
    public String getQuestionByStage(
            @RequestParam String level,
            @RequestParam String section,
            @RequestParam String world,
            @RequestParam String character
    ) {
        List<Question> questions = questionService.findByLevelAndSectionAndWorldAndCharacter(level, section, world, character);
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
