package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.Status;
import com.rubberduck.RubberDuckWebService.repo.StatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    StatusRepo statusRepo;

    @Override
    public List<Status> findByStudentId(Long studentId) {
        return statusRepo.findByStudentId(studentId);
    }

    @Override
    public List<Status> findByStudentIdAndCharacter(Long studentId, String character) {
        return statusRepo.findByStudentIdAndCharacter(studentId, character);
    }

    @Override
    public Status save(Status status) {
        return statusRepo.save(status);
    }

    @Override
    public void delete(Status status) {
        statusRepo.delete(status);
    }
}
