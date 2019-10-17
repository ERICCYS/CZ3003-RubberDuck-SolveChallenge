package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.Status;

import java.util.List;

public interface StatusService {

    List<Status> findByStudentId(Long studentId);

    List<Status> findByStudentIdAndCharacter(Long studentId, String character);

    Status save(Status status);

    void delete(Status status);
}
