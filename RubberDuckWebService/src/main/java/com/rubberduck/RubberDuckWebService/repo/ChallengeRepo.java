package com.rubberduck.RubberDuckWebService.repo;

import com.rubberduck.RubberDuckWebService.model.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepo extends JpaRepository<Challenge, Integer> {

    Challenge findById(Long id);

    Challenge save(Challenge challenge);

    void delete(Challenge challenge);
}
