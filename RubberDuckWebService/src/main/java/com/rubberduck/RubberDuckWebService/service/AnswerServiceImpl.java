package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.Answer;
import com.rubberduck.RubberDuckWebService.repo.AnswerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService{

    @Autowired
    AnswerRepo answerRepo;


    @Override
    public Answer findById(Long id) {
        return answerRepo.findById(id);
    }

    @Override
    public List<Answer> findByStudentId(Long studentId) {
        return answerRepo.findByStudentId(studentId);
    }

    @Override
    public List<Answer> findByQuestionId(Long questionId) {
        return answerRepo.findByQuestionId(questionId);
    }

    @Override
    public Answer save(Answer answer) {
        return answerRepo.save(answer);
    }

    @Override
    public void delete(Answer answer) {
        answerRepo.delete(answer);
    }
}
