package com.rubberduck.RubberDuckWebService.controller;

import com.rubberduck.RubberDuckWebService.JSONConvert;
import com.rubberduck.RubberDuckWebService.model.Student;
import com.rubberduck.RubberDuckWebService.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    public String getAllStudents() {
        List<Student> students = studentService.findAll();
        return JSONConvert.JSONConverter(students);
    }


    @GetMapping("/student")
    public String getStudentById(
            @RequestParam Long id
    ) {
        Student student = studentService.findById(id);
        return JSONConvert.JSONConverter(student);
    }

    @PostMapping("/student")
    @ResponseStatus(HttpStatus.CREATED)
    public String createStudentAccount(
            @Valid @RequestBody Student student
    ) throws NoSuchAlgorithmException {
        String hashedPassword = student.hashPassword(student.getPassword());
        student.setPassword(hashedPassword);
        JSONConvert.JSONConverter(studentService.save(student));
//        return ValidationController.getAccessToken(student.getId(), "CUSTOMER");
        return "OK";
    }

    @GetMapping("/student/signin")
    public String signInStudent(
            @RequestParam String userName,
            @RequestParam String password
    ) throws NoSuchAlgorithmException {
        Student student = studentService.findByUserName(userName);
        if (student == null) {
            throw new IllegalArgumentException();
        }
//        return ValidationController.UserSignIn(student, password);
        return "Finish";
    }
}
