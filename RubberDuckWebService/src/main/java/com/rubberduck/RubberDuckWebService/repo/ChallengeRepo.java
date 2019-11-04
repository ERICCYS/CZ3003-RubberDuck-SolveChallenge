package com.rubberduck.RubberDuckWebService.repo;

import com.rubberduck.RubberDuckWebService.model.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChallengeRepo extends JpaRepository<Challenge, Integer> {

    Challenge findById(Long id);

    List<Challenge> findByCreatorId(Long creatorId);

    @Query(value="SELECT * FROM Challenge WHERE CREATOR_ID = :creator_id ORDER BY ID DESC LIMIT 1", nativeQuery = true)
    Challenge getByCreatorId(
            @Param("creator_id") Long creator_id
    );

    Challenge save(Challenge challenge);

    void delete(Challenge challenge);
}
