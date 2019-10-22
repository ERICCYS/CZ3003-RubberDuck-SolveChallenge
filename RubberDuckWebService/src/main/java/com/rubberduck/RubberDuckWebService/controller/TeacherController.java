package com.rubberduck.RubberDuckWebService.controller;

import com.rubberduck.RubberDuckWebService.JSONConvert;
import com.rubberduck.RubberDuckWebService.ValidationResponse;
import com.rubberduck.RubberDuckWebService.model.Teacher;
import com.rubberduck.RubberDuckWebService.service.TeacherService;
import com.rubberduck.RubberDuckWebService.service.ValidationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    ValidationServiceImpl validationService;

    @GetMapping("/teachers")
    public String getAllTeacher() {
        List<Teacher> teachers = teacherService.findAll();
        return JSONConvert.JSONConverter(teachers);
    }

    @GetMapping("/teacher")
    public String getTeacherById(
            @RequestParam Long id
    ) {
        Teacher teacher = teacherService.findById(id);
        return JSONConvert.JSONConverter(teacher);
    }

    @GetMapping("/teacher/signin")
    public String signInTeacher(
            @RequestParam String userName,
            @RequestParam String password
    ) throws NoSuchAlgorithmException {
        Teacher teacher = teacherService.findByUserName(userName);
        if (teacher == null) {
            throw new IllegalArgumentException();
        }
        String accessToken = validationService.userSignIn(teacher, password);
        Long userId = Long.parseLong(validationService.getUserId(accessToken, "TEACHER"));
        ValidationResponse response = new ValidationResponse(accessToken, userId);
        return JSONConvert.JSONConverter(response);
    }

    @GetMapping("/teacher/getId")
    public String parseTeacherId(
            @RequestHeader(value = "Authorization") String accessToken
    ) {
        System.out.println(accessToken);
        return validationService.getUserId(accessToken, "TEACHER");
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "User name or password incorrect")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badAuthenticationException() {

    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Teacher user name doesn't exist")
    @ExceptionHandler(NullPointerException.class)
    public void notFoundException() {

    }
}
