package com.rubberduck.RubberDuckWebService.controller;

import com.rubberduck.RubberDuckWebService.JSONConvert;
import com.rubberduck.RubberDuckWebService.model.Challenge;
import com.rubberduck.RubberDuckWebService.model.Student;
import com.rubberduck.RubberDuckWebService.service.ChallengeService;
import com.rubberduck.RubberDuckWebService.service.StudentService;
import com.rubberduck.RubberDuckWebService.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ChallengeController {

    @Autowired
    ChallengeService challengeService;

    @Autowired
    ValidationService validationService;

    @Autowired
    StudentService studentService;


    @GetMapping("/challenge")
    public String getChallengeByID(
            @RequestParam Long id
    ) {
        Challenge challenge = challengeService.findById(id);
        return JSONConvert.JSONConverter(challenge);
    }

    @GetMapping("challenge/username")
    public String getChallengeByCreator(
            @RequestParam String userName
    ) {
        Student creator = studentService.findByUserName(userName);
        Challenge challenge;
        if (creator != null) {
            challenge = challengeService.findByCreatorId(creator.getId());
            if (challenge == null) {
                throw new NullPointerException();
            }
        } else {
            throw new NullPointerException();
        }

        return JSONConvert.JSONConverter(challenge);
    }

    @PostMapping("/challenge")
    public String createChallenge(
            @RequestHeader(value = "Authorization") String accessToken,
            @Valid @RequestBody Challenge challenge
    ) {
        if (validationService.getUserId(accessToken, "STUDENT").equals(String.valueOf(challenge.getCreatorId()))) {
            challengeService.addQuestions(challenge);
            return JSONConvert.JSONConverter(challengeService.save(challenge));
        } else {
            throw new IllegalArgumentException();
        }
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Unauthorized")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badAuthenticationException() {

    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Challenge doesn't exist")
    @ExceptionHandler(NullPointerException.class)
    public void notFoundException() {

    }
}
