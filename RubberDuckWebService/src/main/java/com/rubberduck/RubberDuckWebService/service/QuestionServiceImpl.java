package com.rubberduck.RubberDuckWebService.service;

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
    public Question save(Question question) {
        return questionRepo.save(question);
    }

    @Override
    public void delete(Question question) {
        questionRepo.delete(question);
    }
}
