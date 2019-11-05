package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.Teacher;
import com.rubberduck.RubberDuckWebService.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepo teacherRepo;

    @Override
    public List<Teacher> findAll() {
        return teacherRepo.findAll();
    }

    @Override
    public Teacher findById(Long id) {
        return teacherRepo.findById(id);
    }

    @Override
    public Teacher findByUserName(String userName) {
        return teacherRepo.findByUserName(userName);
    }

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepo.save(teacher);
    }

    @Override
    public void delete(Teacher teacher) {
        teacherRepo.delete(teacher);
    }
}
