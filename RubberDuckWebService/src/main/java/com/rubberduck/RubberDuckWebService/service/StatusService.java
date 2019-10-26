package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.Status;
import javafx.util.Pair;

import java.util.List;

public interface StatusService {

    List<Status> findByStudentId(Long studentId);

    Status findByStudentIdAndCharacter(Long studentId, String character);

    Status getCurrentStatus(Long studentId, String character);

    String[] getNextStage(Status status);

    Status updateStatus(Long studentId, String character);

    List<String> getWorlds();

    List<Pair<String, String>> getWorldAndSection();

    Status save(Status status);

    void delete(Status status);
}
