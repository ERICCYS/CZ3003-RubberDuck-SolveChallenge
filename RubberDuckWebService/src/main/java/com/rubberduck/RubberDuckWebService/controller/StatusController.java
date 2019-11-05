package com.rubberduck.RubberDuckWebService.controller;

import com.rubberduck.RubberDuckWebService.JSONConvert;
import com.rubberduck.RubberDuckWebService.service.StatusService;
import com.rubberduck.RubberDuckWebService.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class StatusController {

    @Autowired
    StatusService statusService;

    @Autowired
    ValidationService validationService;

    @GetMapping("status/update")
    public String updateStudentStatus(
            @RequestParam Long studentId,
            @RequestParam String character,
            @RequestHeader(value = "Authorization") String accessToken
    ) {
        if (Long.parseLong(validationService.getUserId(accessToken, "STUDENT")) > 0) {
            return JSONConvert.JSONConverter(statusService.updateStatus(studentId, character));
        } else {
            throw new IllegalArgumentException();
        }
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Not authorized to update the status")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badAuthenticationException() {

    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found")
    @ExceptionHandler(NullPointerException.class)
    public void notFoundException() {

    }
}
