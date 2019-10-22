package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.Status;
import com.rubberduck.RubberDuckWebService.repo.StatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    StatusRepo statusRepo;

    public static final Map<String, Integer> worldCodes = new HashMap<String, Integer>(){
        {
            put("Requirement Gathering and Analysis", 0);
            put("Design", 1);
            put("Implementation", 2);
            put("Testing and Deployment", 3);
            put("Maintenance", 4);
        }
    };

    public static final List<Map<String, Integer>> sectionCodes = new ArrayList<Map<String, Integer>>(){
        {
            add(new HashMap<String, Integer>() {{
                put("Requirement Engineering", 0);
                put("Requirement Analysis", 1);
                put("Requirement Specification and Validations", 2);
            }});
            add(new HashMap<String, Integer>() {{
                put("Design Phrase I", 0);
                put("Design Phrase II", 1);
                put("Design Phrase III", 2);
            }});
            add(new HashMap<String, Integer>() {{
                put("Implementation FE Dev", 0);
                put("Implementation BE Dev", 1);
                put("Implementation ALL", 2);
            }});
            add(new HashMap<String, Integer>() {{
                put("Testing", 0);
                put("Deployment", 1);
                put("DevOps", 2);
            }});
            add(new HashMap<String, Integer>() {{
                put("Maintenance I", 0);
                put("Maintenance II", 1);
                put("Maintenance III", 2);
            }});
        }
    };

    @Override
    public List<Status> findByStudentId(Long studentId) {
        return statusRepo.findByStudentId(studentId);
    }

    @Override
    public Status findByStudentIdAndCharacter(Long studentId, String character) {
        Status status = statusRepo.findByStudentIdAndCharacter(studentId, character);
        Integer worldCode = worldCodes.get(status.getWorld());
        status.setWorld(worldCode.toString());
        Integer sectionCode = sectionCodes.get(worldCode).get(status.getSection());
        status.setSection(sectionCode.toString());
        String level = status.getLevel();
        if (level.equals("EASY")) {
            status.setLevel("0");
        }
        if (level.equals("MEDIUM")) {
            status.setLevel("1");
        }
        if (level.equals("HARD")) {
            status.setLevel("2");
        }
        return status;
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
