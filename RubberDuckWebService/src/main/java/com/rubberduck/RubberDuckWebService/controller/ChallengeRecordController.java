package com.rubberduck.RubberDuckWebService.controller;


import com.rubberduck.RubberDuckWebService.JSONConvert;
import com.rubberduck.RubberDuckWebService.model.ChallengeRecord;
import com.rubberduck.RubberDuckWebService.service.ChallengeRecordService;
import com.rubberduck.RubberDuckWebService.service.ChallengeService;
import com.rubberduck.RubberDuckWebService.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChallengeRecordController {

    @Autowired
    ChallengeRecordService challengeRecordService;

    @Autowired
    ChallengeService challengeService;

    @Autowired
    ValidationService validationService;

    @GetMapping("challenge/record")
    public String getChallengeRecordById(
            @RequestParam Long id
    ) {
        ChallengeRecord challengeRecord = challengeRecordService.findById(id);
        return JSONConvert.JSONConverter(challengeRecord);
    }

    @PostMapping("challenge/record")
    public String saveChallengeRecord(
            @RequestBody ChallengeRecord challengeRecord,
            @RequestHeader(value = "Authorization") String accessToken
    ) {
        if (Long.parseLong(validationService.getUserId(accessToken, "STUDENT")) == challengeRecord.getChallengerId()) {
            boolean isSuccess = challengeRecord.isSuccess();
            Long challengeId = challengeRecord.getChallengeId();
            if (isSuccess) {
                challengeService.increaseSuccess(challengeId);
            } else {
                challengeService.increaseFail(challengeId);
            }
            return JSONConvert.JSONConverter(challengeRecordService.save(challengeRecord));
        } else {
            throw new IllegalArgumentException();
        }
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Not authorized to create challenge record")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badAuthenticationException() {

    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Data not found")
    @ExceptionHandler(NullPointerException.class)
    public void notFoundException() {

    }
}
