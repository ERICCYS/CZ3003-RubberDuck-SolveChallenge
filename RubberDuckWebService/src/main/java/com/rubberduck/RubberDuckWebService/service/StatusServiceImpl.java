package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.Status;
import com.rubberduck.RubberDuckWebService.repo.StatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatusServiceImpl implements StatusService {

    private static final Map<String, Integer> worldCodes = new LinkedHashMap<String, Integer>() {
        {
            put("Requirement Gathering and Analysis", 0);
            put("Design", 1);
            put("Implementation", 2);
            put("Testing and Deployment", 3);
            put("Maintenance", 4);
        }
    };

    private static final List<Map<String, Integer>> sectionCodes = new ArrayList<Map<String, Integer>>() {
        {
            add(new LinkedHashMap<String, Integer>() {{
                put("Requirement Engineering", 0);
                put("Requirement Analysis", 1);
                put("Requirement Specification and Validations", 2);
            }});
            add(new LinkedHashMap<String, Integer>() {{
                put("Design Phrase I", 0);
                put("Design Phrase II", 1);
                put("Design Phrase III", 2);
            }});
            add(new LinkedHashMap<String, Integer>() {{
                put("Implementation FE Dev", 0);
                put("Implementation BE Dev", 1);
                put("Implementation ALL", 2);
            }});
            add(new LinkedHashMap<String, Integer>() {{
                put("Testing", 0);
                put("Deployment", 1);
                put("DevOps", 2);
            }});
            add(new LinkedHashMap<String, Integer>() {{
                put("Maintenance I", 0);
                put("Maintenance II", 1);
                put("Maintenance III", 2);
            }});
        }
    };

    @Autowired
    StatusRepo statusRepo;

    @Override
    public String[] getNextStage(Status status) {
        String[] nextStage = new String[3];
        Integer worldCode = worldCodes.get(status.getWorld());
        Integer sectionCode = sectionCodes.get(worldCode).get(status.getSection());
        String level = status.getLevel();
        String nextLevel = status.getLevel();
        String nextWorld = status.getWorld();
        String nextSection = status.getSection();
        if (level.equals("EASY")) {
            nextLevel = "MEDIUM";
        } else if (level.equals("MEDIUM")) {
            nextLevel = "HARD";
        } else if (level.equals("HARD")) {
            if (sectionCode != 2) {
                for (Map.Entry<String, Integer> sectionInfo : sectionCodes.get(worldCode).entrySet()) {
                    if (sectionInfo.getValue() == (sectionCode + 1)) {
                        nextSection = sectionInfo.getKey();
                    }
                }
                nextLevel = "EASY";
            } else {
                if (worldCode < 4) {
                    for (Map.Entry<String, Integer> worldInfo : worldCodes.entrySet()) {
                        if (worldInfo.getValue() == (worldCode + 1)) {
                            nextWorld = worldInfo.getKey();
                        }
                    }
                    for (Map.Entry<String, Integer> sectionInfo : sectionCodes.get(worldCode + 1).entrySet()) {
                        if (sectionInfo.getValue() == 0) {
                            nextSection = sectionInfo.getKey();
                        }
                    }
                    nextLevel = "EASY";
                }
            }
        }
        nextStage[0] = nextWorld;
        nextStage[1] = nextSection;
        nextStage[2] = nextLevel;
        return nextStage;
    }

    @Override
    public List<Status> findByStudentId(Long studentId) {
        return statusRepo.findByStudentId(studentId);
    }

    @Override
    public Status findByStudentIdAndCharacter(Long studentId, String character) {
        return statusRepo.findByStudentIdAndCharacter(studentId, character);
    }

    @Override
    public Status getCurrentStatus(Long studentId, String character) {
        Status status = statusRepo.findByStudentIdAndCharacter(studentId, character);
        if (status == null) {
            status = new Status(studentId, character, "Requirement Gathering and Analysis", "Requirement Engineering", "EASY");
            statusRepo.save(status);
            Status resultStatus = new Status(status, status.getId());
            resultStatus.setWorld("0");
            resultStatus.setSection("0");
            resultStatus.setLevel("0");
            status = statusRepo.findByStudentIdAndCharacter(studentId, character);
            resultStatus.setId(status.getId());
            return resultStatus;
        }
        Status resultStatus = new Status(status, status.getId());
        Integer worldCode = worldCodes.get(status.getWorld());
        resultStatus.setWorld(worldCode.toString());
        Integer sectionCode = sectionCodes.get(worldCode).get(status.getSection());
        resultStatus.setSection(sectionCode.toString());
        String level = status.getLevel();
        if (level.equals("EASY")) {
            resultStatus.setLevel("0");
        }
        if (level.equals("MEDIUM")) {
            resultStatus.setLevel("1");
        }
        if (level.equals("HARD")) {
            resultStatus.setLevel("2");
        }
        return resultStatus;
    }

    @Override
    public Status updateStatus(Long studentId, String character) {
        Status status = statusRepo.findByStudentIdAndCharacter(studentId, character);
        String[] nextStage = getNextStage(status);
        String nextWorld = nextStage[0];
        String nextSection = nextStage[1];
        String nextLevel = nextStage[2];
        status.setLevel(nextLevel);
        status.setSection(nextSection);
        status.setWorld(nextWorld);
        statusRepo.save(status);
        return statusRepo.save(status);
    }

    @Override
    public List<String> getWorlds() {
        List<String> worlds = new ArrayList<>();
        String world;
        for (Map.Entry<String, Integer> worldInfo : worldCodes.entrySet()) {
            world = worldInfo.getKey();
            worlds.add(world);
        }
        return worlds;
    }

    @Override
    public Map<String, List<String>> getWorldAndSection() {
        Map<String, List<String>> worldSections = new LinkedHashMap<>();
        String world;
        String section;
        for (Map.Entry<String, Integer> worldInfo : worldCodes.entrySet()) {
            world = worldInfo.getKey();
            List<String> sectionList = new ArrayList<>();
            for (Map.Entry<String, Integer> sectionInfo : sectionCodes.get(worldInfo.getValue()).entrySet()) {
                section = sectionInfo.getKey();
                sectionList.add(section);
            }
            worldSections.put(world, sectionList);
        }
        return worldSections;
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
