package com.rubberduck.RubberDuckWebService.controller;

import com.rubberduck.RubberDuckWebService.JSONConvert;
import com.rubberduck.RubberDuckWebService.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @Autowired
    StatusService statusService;

    @GetMapping("status/update")
    public String updateStudentStatus(
            @RequestParam Long studentId,
            @RequestParam String character
    ) {
        return JSONConvert.JSONConverter(statusService.updateStatus(studentId, character));
    }
}
