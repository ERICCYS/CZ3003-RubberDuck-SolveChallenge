package com.rubberduck.RubberDuckWebService.repo;

import com.rubberduck.RubberDuckWebService.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Question, Integer> {

    Question findById(Long id);

    List<Question> findByLevel(String level);

    Question save(Question question);

    void delete(Question question);
}
