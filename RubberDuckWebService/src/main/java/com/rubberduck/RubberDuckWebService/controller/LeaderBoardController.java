package com.rubberduck.RubberDuckWebService.controller;

import com.rubberduck.RubberDuckWebService.JSONConvert;
import com.rubberduck.RubberDuckWebService.model.Student;
import com.rubberduck.RubberDuckWebService.service.LeaderBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LeaderBoardController {

    @Autowired
    LeaderBoardService leaderBoardService;

    @GetMapping("/leaderboard")
    public String getLeaderBoard() {
        List<Student> leaderBoardInfo = leaderBoardService.getLeaderBoard();
        return JSONConvert.JSONConverter(leaderBoardInfo);
    }
}
