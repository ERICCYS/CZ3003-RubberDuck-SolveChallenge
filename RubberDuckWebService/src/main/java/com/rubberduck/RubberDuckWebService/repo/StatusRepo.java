package com.rubberduck.RubberDuckWebService.repo;

import com.rubberduck.RubberDuckWebService.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepo extends JpaRepository<Status, Integer> {

    List<Status> findByStudentId(Long studentId);

    Status findByStudentIdAndCharacter(Long studentId, String character);

    Status save(Status status);

    void delete(Status status);
}
