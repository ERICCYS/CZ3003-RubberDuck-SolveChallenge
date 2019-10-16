package com.rubberduck.RubberDuckWebService.repo;

import com.rubberduck.RubberDuckWebService.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Integer> {

    Student findById(Long id);

    Student findByUserName(String userName);

    Student save(Student student);

    void delete(Student student);
}
