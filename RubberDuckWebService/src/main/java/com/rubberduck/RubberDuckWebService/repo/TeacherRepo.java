package com.rubberduck.RubberDuckWebService.repo;

import com.rubberduck.RubberDuckWebService.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teacher, Integer> {
    Teacher findById(Long id);

    Teacher findByUserName(Long userName);

    Teacher save(Teacher teacher);

    void delete(Teacher teacher);
}
