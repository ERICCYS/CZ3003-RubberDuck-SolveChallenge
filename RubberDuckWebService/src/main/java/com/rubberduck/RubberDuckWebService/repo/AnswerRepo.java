package com.rubberduck.RubberDuckWebService.repo;

import com.rubberduck.RubberDuckWebService.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepo extends JpaRepository<Answer, Integer> {

    Answer findById(Long id);

    List<Answer> findByStudentId(Long studentId);

    List<Answer> findByQuestionId(Long questionId);

    List<Answer> findByQuestionIdAndMode(Long questionId, String mode);

    List<Answer> findByStudentIdAndQuestionIdAndMode(Long studentId, Long questionId, String mode);

    Answer save(Answer answer);

    void delete(Answer answer);
}
