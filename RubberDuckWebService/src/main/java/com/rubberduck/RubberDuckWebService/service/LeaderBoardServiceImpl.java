package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.Student;
import com.rubberduck.RubberDuckWebService.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class LeaderBoardServiceImpl implements LeaderBoardService {

    @Autowired
    StudentRepo studentRepo;

    @Override
    public List<Student> getLeaderBoard() {
        List<Student> students = studentRepo.findAll();
        students.sort(Comparator.comparing(Student::getMark));
        Collections.reverse(students);
        return students;
    }
}
