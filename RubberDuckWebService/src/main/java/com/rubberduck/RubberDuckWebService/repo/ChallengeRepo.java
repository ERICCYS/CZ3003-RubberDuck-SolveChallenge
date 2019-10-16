package com.rubberduck.RubberDuckWebService.repo;

import com.rubberduck.RubberDuckWebService.model.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeRepo extends JpaRepository<Challenge, Integer> {

    Challenge findById(Long id);

    List<Challenge> findByCreatorId(Long creatorId);

    Challenge save(Challenge challenge);

    void delete(Challenge challenge);
}
