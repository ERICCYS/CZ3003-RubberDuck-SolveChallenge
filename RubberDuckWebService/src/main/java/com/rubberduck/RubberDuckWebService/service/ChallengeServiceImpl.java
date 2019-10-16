package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.Challenge;
import com.rubberduck.RubberDuckWebService.repo.ChallengeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    @Autowired
    ChallengeRepo challengeRepo;


    @Override
    public Challenge findById(Long id) {
        return challengeRepo.findById(id);
    }

    @Override
    public List<Challenge> findByCreatorId(Long creatorId) {
        return challengeRepo.findByCreatorId(creatorId);
    }

    @Override
    public Challenge save(Challenge challenge) {
        return challengeRepo.save(challenge);
    }

    @Override
    public void delete(Challenge challenge) {
        challengeRepo.delete(challenge);
    }
}
