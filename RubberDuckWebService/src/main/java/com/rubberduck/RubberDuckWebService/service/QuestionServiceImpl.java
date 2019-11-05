package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.DifficultyEnum;
import com.rubberduck.RubberDuckWebService.model.Question;
import com.rubberduck.RubberDuckWebService.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepo questionRepo;

    @Override
    public List<Question> findAll() {
        return questionRepo.findAll();
    }

    @Override
    public Question findById(Long id) {
        return questionRepo.findById(id);
    }

    @Override
    public List<Question> findByLevel(String level) {
        return questionRepo.findByLevel(level);
    }

    @Override
    public List<Question> findByWorld(String world) {
        return questionRepo.findByWorld(world);
    }

    @Override
    public List<Question> findBySectionAndWorld(String section, String world) {
        return questionRepo.findBySectionAndWorld(section, world);
    }

    @Override
    public List<Question> findByLevelAndSectionAndWorld(String level, String section, String world) {
        return questionRepo.findByLevelAndSectionAndWorld(level, section, world);
    }

    @Override
    public List<Question> findByCharacter(String character) {
        return questionRepo.findByCharacter(character);
    }

    @Override
    public List<Question> findByLevelAndSectionAndWorldAndCharacter(String level, String section, String world, String character) {
        return questionRepo.findByLevelAndSectionAndWorldAndCharacter(level, section, world, character);
    }

    @Override
    public List<Question> findByCharacterAndWorldAndDifficulty(String character, String world, DifficultyEnum difficultyEnum) {
        return questionRepo.findByCharacterAndWorldAndDifficulty(character, world, difficultyEnum);
    }

    @Override
    public Question save(Question question) {
        return questionRepo.save(question);
    }

    @Override
    public void delete(Question question) {
        questionRepo.delete(question);
    }
}
