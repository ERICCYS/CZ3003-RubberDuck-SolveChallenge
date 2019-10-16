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
    public String getAllStudents() {
        List<Teacher> teachers = teacherService.findAll();
        return JSONConvert.JSONConverter(teachers);
    }


    @GetMapping("/teacher")
    public String getStudentById(
            @RequestParam Long id
    ) {
        Teacher teacher = teacherService.findById(id);
        return JSONConvert.JSONConverter(teacher);
    }

    @PostMapping("/teacher")
    @ResponseStatus(HttpStatus.CREATED)
    public String createStudentAccount(
            @Valid @RequestBody Teacher teacher
    ) throws NoSuchAlgorithmException {
        String hashedPassword = teacher.hashPassword(teacher.getPassword());
        teacher.setPassword(hashedPassword);
        JSONConvert.JSONConverter(teacherService.save(teacher));
//        return ValidationController.getAccessToken(teacher.getId(), "CUSTOMER");
        return "OK";
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
}
