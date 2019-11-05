package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.ChallengeRecord;

import java.util.List;

public interface ChallengeRecordService {

    ChallengeRecord findById(Long id);

    List<ChallengeRecord> findByChallengerId(Long challengerId);

    List<ChallengeRecord> findByChallengeId(Long challengeId);

    ChallengeRecord save(ChallengeRecord challengeRecord);

    void delete(ChallengeRecord challengeRecord);
}
