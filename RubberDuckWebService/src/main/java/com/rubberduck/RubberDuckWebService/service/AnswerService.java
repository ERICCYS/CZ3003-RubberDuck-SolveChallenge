package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.Answer;

import java.util.List;

public interface AnswerService {

    Answer findById(Long id);

    List<Answer> findByStudentId(Long studentId);

    List<Answer> findByQuestionId(Long questionId);

    boolean checkAnswer(Answer answer);

    Object save(Answer answer);

    void delete(Answer answer);
}
