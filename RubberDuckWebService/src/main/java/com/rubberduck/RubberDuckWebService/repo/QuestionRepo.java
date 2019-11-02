package com.rubberduck.RubberDuckWebService.repo;

import com.rubberduck.RubberDuckWebService.model.DifficultyEnum;
import com.rubberduck.RubberDuckWebService.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Question, Integer> {

    Question findById(Long id);

    List<Question> findByLevel(String level);

    List<Question> findByWorld(String world);

    List<Question> findBySectionAndWorld(String section, String world);

    List<Question> findByLevelAndSectionAndWorld(String level, String section, String world);

    List<Question> findByCharacter(String character);

    List<Question> findByLevelAndSectionAndWorldAndCharacter(String level, String section, String world, String character);

    List<Question> findByCharacterAndWorldAndDifficulty(String character, String world, DifficultyEnum difficultyEnum);

    @Query(value="SELECT * FROM Question WHERE CHARACTER_CHOICE = :character_choice AND WORLD = :world ORDER BY RAND() LIMIT :lim", nativeQuery = true)
    List<Question> samplingQuestion(
            @Param("character_choice") String character_choice,
            @Param("world") String world,
            @Param("lim") int lim
    );

    Question save(Question question);

    void delete(Question question);
}
