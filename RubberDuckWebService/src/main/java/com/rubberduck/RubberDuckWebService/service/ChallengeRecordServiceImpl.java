package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.ChallengeRecord;
import com.rubberduck.RubberDuckWebService.repo.ChallengeRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeRecordServiceImpl implements ChallengeRecordService {

    @Autowired
    ChallengeRecordRepo challengeRecordRepo;


    @Override
    public ChallengeRecord findById(Long id) {
        return challengeRecordRepo.findById(id);
    }

    @Override
    public List<ChallengeRecord> findByChallengerId(Long challengerId) {
        return challengeRecordRepo.findByChallengerId(challengerId);
    }

    @Override
    public List<ChallengeRecord> findByChallengeId(Long challengeId) {
        return challengeRecordRepo.findByChallengeId(challengeId);
    }

    @Override
    public ChallengeRecord save(ChallengeRecord challengeRecord) {
        return challengeRecordRepo.save(challengeRecord);
    }

    @Override
    public void delete(ChallengeRecord challengeRecord) {
        challengeRecordRepo.save(challengeRecord);
    }
}
