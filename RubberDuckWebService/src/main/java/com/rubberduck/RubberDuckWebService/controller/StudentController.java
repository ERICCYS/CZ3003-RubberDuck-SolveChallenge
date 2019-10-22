package com.rubberduck.RubberDuckWebService.controller;

import com.rubberduck.RubberDuckWebService.JSONConvert;
import com.rubberduck.RubberDuckWebService.ValidationResponse;
import com.rubberduck.RubberDuckWebService.model.Student;
import com.rubberduck.RubberDuckWebService.service.StudentService;
import com.rubberduck.RubberDuckWebService.service.ValidationService;
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

    @Autowired
    ValidationService validationService;

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
        String accessToken = validationService.getAccessToken(student, "STUDENT");
        Long userId = Long.parseLong(validationService.getUserId(accessToken, "STUDENT"));
        ValidationResponse response = new ValidationResponse(accessToken, userId);
        return JSONConvert.JSONConverter(response);
    }

    @GetMapping("/student/signin")
    public String signInStudent(
            @RequestParam String userName,
            @RequestParam String password
    ) throws NoSuchAlgorithmException {
        Student student = studentService.findByUserName(userName);
        if (student == null) {
            throw new NullPointerException();
        }
        String accessToken = validationService.userSignIn(student, password);
        Long userId = Long.parseLong(validationService.getUserId(accessToken, "STUDENT"));
        ValidationResponse response = new ValidationResponse(accessToken, userId);
        return JSONConvert.JSONConverter(response);
    }

    @GetMapping("student/username")
    public String getStudentByUserName(
            @RequestParam String userName
    ) throws NullPointerException {
        Student student = studentService.findByUserName(userName);
        if (student == null) {
            throw new NullPointerException();
        }
        return JSONConvert.JSONConverter(student);
    }

    @GetMapping("/student/getId")
    public String parseStudentId(
            @RequestHeader(value = "Authorization") String accessToken
    ) {
        System.out.println(accessToken);
        return validationService.getUserId(accessToken, "STUDENT");
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "User name or password incorrect")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badAuthenticationException() {

    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Student user name doesn't exist")
    @ExceptionHandler(NullPointerException.class)
    public void notFoundException() {

    }
}
