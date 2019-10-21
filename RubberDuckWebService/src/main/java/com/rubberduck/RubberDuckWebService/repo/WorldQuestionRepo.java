package com.rubberduck.RubberDuckWebService.repo;

import com.rubberduck.RubberDuckWebService.model.WorldQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorldQuestionRepo extends JpaRepository<WorldQuestion, Integer> {

    WorldQuestion save (WorldQuestion worldQuestion);

}
