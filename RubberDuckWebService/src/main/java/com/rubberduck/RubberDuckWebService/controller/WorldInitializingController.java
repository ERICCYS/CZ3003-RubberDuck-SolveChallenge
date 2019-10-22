package com.rubberduck.RubberDuckWebService.controller;

import com.rubberduck.RubberDuckWebService.JSONConvert;
import com.rubberduck.RubberDuckWebService.model.Status;
import com.rubberduck.RubberDuckWebService.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorldInitializingController {

    @Autowired
    StatusService statusService;

    @GetMapping("world/initialize")
    public String initializeWorldWithCharacter(
            @RequestParam Long studentId,
            @RequestParam String character
    ) {
        Status studentStatusWithChar = statusService.findByStudentIdAndCharacter(studentId, character);
        return JSONConvert.JSONConverter(studentStatusWithChar);
    }
}
