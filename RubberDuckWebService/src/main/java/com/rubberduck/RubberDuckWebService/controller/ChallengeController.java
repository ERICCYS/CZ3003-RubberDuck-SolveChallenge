package com.rubberduck.RubberDuckWebService.controller;

import com.rubberduck.RubberDuckWebService.JSONConvert;
import com.rubberduck.RubberDuckWebService.model.Challenge;
import com.rubberduck.RubberDuckWebService.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ChallengeController {

    @Autowired
    ChallengeService challengeService;


    @GetMapping("/challenge")
    public String getChallengeByID(
            @RequestParam Long id
    ) {
        Challenge challenge = challengeService.findById(id);
        return JSONConvert.JSONConverter(challenge);
    }

    @PostMapping("/challenge")
    public String createChallenge(
            @Valid @RequestBody Challenge challenge
    ) {
        // TODO: validate the student identity

        // TODO: do the sampling of the questions according to the rule

        return JSONConvert.JSONConverter(challengeService.save(challenge));
    }
}
