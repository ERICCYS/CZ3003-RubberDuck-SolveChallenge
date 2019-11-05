package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> findAll();

    Teacher findById(Long id);

    Teacher findByUserName(String userName);

    Teacher save(Teacher teacher);

    void delete(Teacher teacher);
}
