package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.Challenge;
import com.rubberduck.RubberDuckWebService.model.WorldQuestion;
import com.rubberduck.RubberDuckWebService.repo.ChallengeRepo;
import com.rubberduck.RubberDuckWebService.repo.WorldQuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    @Autowired
    ChallengeRepo challengeRepo;

    @Autowired
    WorldQuestionRepo worldQuestionRepo;


    @Override
    public Challenge findById(Long id) {
        return challengeRepo.findById(id);
    }

    @Override
    public List<Challenge> findByCreatorId(Long creatorId) {
        return challengeRepo.findByCreatorId(creatorId);
    }

    @Override
    public void addQuestions(Challenge challenge) {
        // get challenge character
        String character = challenge.getCharacter();

        // get challenge

    }

    @Override
    public Challenge save(Challenge challenge) {
        List<WorldQuestion> worldQuestions = challenge.getWorldQuestion();
        for (WorldQuestion worldQuestion : worldQuestions) {
            worldQuestionRepo.save(worldQuestion);
        }
        return challengeRepo.save(challenge);
    }

    @Override
    public void delete(Challenge challenge) {
        challengeRepo.delete(challenge);
    }
}
