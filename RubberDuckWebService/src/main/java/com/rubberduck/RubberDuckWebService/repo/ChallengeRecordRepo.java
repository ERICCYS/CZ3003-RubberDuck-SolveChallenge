package com.rubberduck.RubberDuckWebService.repo;

import com.rubberduck.RubberDuckWebService.model.ChallengeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChallengeRecordRepo extends JpaRepository<ChallengeRecord, Integer> {

    ChallengeRecord findById(Long id);

    List<ChallengeRecord> findByChallengerId(Long challengerId);

    List<ChallengeRecord> findByChallengeId(Long challengeId);

    ChallengeRecord save(ChallengeRecord challengeRecord);

    void delete(ChallengeRecord challengeRecord);
}
