package com.rubberduck.RubberDuckWebService.controller;

import com.rubberduck.RubberDuckWebService.JSONConvert;
import com.rubberduck.RubberDuckWebService.model.Student;
import com.rubberduck.RubberDuckWebService.service.LeaderBoardService;
import com.rubberduck.RubberDuckWebService.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LeaderBoardController {

    @Autowired
    LeaderBoardService leaderBoardService;

    @Autowired
    ValidationService validationService;

    @GetMapping("/leaderboard")
    public String getLeaderBoard(
            @RequestHeader(value = "Authorization") String accessToken
    ) {
        if (Long.parseLong(validationService.getUserId(accessToken, "STUDENT")) > 0 || Long.parseLong(validationService.getUserId(accessToken, "TEACHER")) > 0) {
            List<Student> leaderBoardInfo = leaderBoardService.getLeaderBoard();
            return JSONConvert.JSONConverter(leaderBoardInfo);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Not authorized to view the leaderboard")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badAuthenticationException() {

    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Data not found")
    @ExceptionHandler(NullPointerException.class)
    public void notFoundException() {

    }
}
