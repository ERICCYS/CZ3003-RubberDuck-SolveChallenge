package com.rubberduck.RubberDuckWebService.controller;

import com.rubberduck.RubberDuckWebService.JSONConvert;
import com.rubberduck.RubberDuckWebService.model.Answer;
import com.rubberduck.RubberDuckWebService.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @GetMapping("/answer")
    public String getAnswerById(
            @RequestParam Long id
    ) {
        Answer answer = answerService.findById(id);
        return JSONConvert.JSONConverter(answer);
    }

    @GetMapping("/answer/student")
    public String getAnswersByStudentId(
            @RequestParam Long studentId
    ) {
        List<Answer> answers = answerService.findByStudentId(studentId);
        return JSONConvert.JSONConverter(answers);
    }

    @GetMapping("/answer/question")
    public String getAnswersByQuestionId(
            @RequestParam Long questionId
    ) {
        List<Answer> answers = answerService.findByQuestionId(questionId);
        return JSONConvert.JSONConverter(answers);
    }

    @PostMapping("/answer")
    public String createAnswer(
            @Valid @RequestBody Answer answer
    ) {
        return JSONConvert.JSONConverter(answerService.save(answer));
    }
}
