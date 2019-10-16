package com.rubberduck.RubberDuckWebService.repo;

import com.rubberduck.RubberDuckWebService.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepo extends JpaRepository<Answer, Integer> {
    Answer findById(Long id);

    Answer save(Answer answer);

    void delete(Answer answer);
}
