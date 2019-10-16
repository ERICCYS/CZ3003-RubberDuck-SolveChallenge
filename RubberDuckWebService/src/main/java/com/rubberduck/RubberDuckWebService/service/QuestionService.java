package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.Question;

import java.util.List;

public interface QuestionService {

    List<Question> findAll();

    Question findById(Long id);

    List<Question> findByLevel(String level);

    Question save(Question question);

    void delete(Question question);
}
