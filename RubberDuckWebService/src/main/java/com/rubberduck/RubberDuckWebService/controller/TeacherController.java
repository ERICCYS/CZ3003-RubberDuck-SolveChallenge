package com.rubberduck.RubberDuckWebService.controller;

import com.rubberduck.RubberDuckWebService.JSONConvert;
import com.rubberduck.RubberDuckWebService.model.Teacher;
import com.rubberduck.RubberDuckWebService.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class TeacherController {

    @Autowired
    TeacherService teacherService;

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
//        return ValidationController.UserSignIn(teacher, password);
        return "Finish";
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "User name or password incorrect")
    @ExceptionHandler(IllegalAccessException.class)
    public void badAuthenticationException() {

    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Student user name doesn't exist")
    @ExceptionHandler(NullPointerException.class)
    public void notFoundException() {

    }
}
