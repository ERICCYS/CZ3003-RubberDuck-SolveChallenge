package com.rubberduck.RubberDuckWebService.repo;

import com.rubberduck.RubberDuckWebService.model.ChallengeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRecordRepo extends JpaRepository<ChallengeRecord, Integer> {

    ChallengeRecord findById(Long id);

    ChallengeRecord save(ChallengeRecord challengeRecord);

    void delete(ChallengeRecord challengeRecord);
}
