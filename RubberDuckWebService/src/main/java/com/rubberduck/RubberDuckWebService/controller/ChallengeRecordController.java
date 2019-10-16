package com.rubberduck.RubberDuckWebService.controller;


import com.rubberduck.RubberDuckWebService.JSONConvert;
import com.rubberduck.RubberDuckWebService.model.ChallengeRecord;
import com.rubberduck.RubberDuckWebService.service.ChallengeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChallengeRecordController {

    @Autowired
    ChallengeRecordService challengeRecordService;

    @GetMapping("challenge/record")
    public String getChallengeRecordById(
            @RequestParam Long id
    ) {
        ChallengeRecord challengeRecord = challengeRecordService.findById(id);
        return JSONConvert.JSONConverter(challengeRecord);
    }
}
