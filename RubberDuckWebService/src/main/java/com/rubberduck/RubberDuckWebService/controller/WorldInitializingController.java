package com.rubberduck.RubberDuckWebService.controller;

import com.rubberduck.RubberDuckWebService.JSONConvert;
import com.rubberduck.RubberDuckWebService.model.Status;
import com.rubberduck.RubberDuckWebService.service.StatusService;
import com.rubberduck.RubberDuckWebService.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class WorldInitializingController {

    @Autowired
    StatusService statusService;

    @Autowired
    ValidationService validationService;

    @GetMapping("world/initialize")
    public String initializeWorldWithCharacter(
            @RequestParam Long studentId,
            @RequestParam String character,
            @RequestHeader(value = "Authorization") String accessToken
    ) {
        if (Long.parseLong(validationService.getUserId(accessToken, "STUDENT")) == studentId) {
            Status studentStatusWithChar = statusService.getCurrentStatus(studentId, character);
            return JSONConvert.JSONConverter(studentStatusWithChar);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Not authorized to initialize the world")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badAuthenticationException() {

    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Data not found")
    @ExceptionHandler(NullPointerException.class)
    public void notFoundException() {

    }
}
