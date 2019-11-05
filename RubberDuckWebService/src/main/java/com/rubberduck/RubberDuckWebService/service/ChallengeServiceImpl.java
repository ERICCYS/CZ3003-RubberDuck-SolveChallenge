package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.Challenge;
import com.rubberduck.RubberDuckWebService.model.DifficultyEnum;
import com.rubberduck.RubberDuckWebService.model.Question;
import com.rubberduck.RubberDuckWebService.model.WorldQuestion;
import com.rubberduck.RubberDuckWebService.repo.ChallengeRepo;
import com.rubberduck.RubberDuckWebService.repo.QuestionRepo;
import com.rubberduck.RubberDuckWebService.repo.WorldQuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    @Autowired
    ChallengeRepo challengeRepo;

    @Autowired
    WorldQuestionRepo worldQuestionRepo;

    @Autowired
    QuestionRepo questionRepo;


    @Override
    public Challenge findById(Long id) {
        return challengeRepo.findById(id);
    }

    @Override
    public Challenge findByCreatorId(Long creatorId) {
        return challengeRepo.getByCreatorId(creatorId);
    }

    @Override
    public void addQuestions(Challenge challenge) {
        String character = challenge.getCharacter();
        List<WorldQuestion> worldQuestions = challenge.getWorldQuestion();
        System.out.println(worldQuestions);
        List<Long> questionIds = new ArrayList<>();

        for (WorldQuestion worldQuestion : worldQuestions) {
            String world = worldQuestion.getWorld();
            Integer questionCount = worldQuestion.getCount();
            System.out.println(character + " " + world + " " + questionCount);
            List<Question> sampledQuestion = questionRepo.samplingQuestion(character, world, questionCount);
            System.out.println(sampledQuestion.size());
            for (Question question : sampledQuestion) {
                questionIds.add(question.getId());
            }
        }
        challenge.setQuestionIds(questionIds);
    }

    @Override
    public void increaseSuccess(Long challengeId) {
        Challenge challenge = challengeRepo.findById(challengeId);
        challenge.increaseSuccessCount();
        challengeRepo.save(challenge);
    }

    @Override
    public void increaseFail(Long challengeId) {
        Challenge challenge = challengeRepo.findById(challengeId);
        challenge.increaseFailureCount();
        challengeRepo.save(challenge);
    }

    @Override
    public List<Question> save(Challenge challenge) {
        List<WorldQuestion> worldQuestions = challenge.getWorldQuestion();
        for (WorldQuestion worldQuestion : worldQuestions) {
            worldQuestionRepo.save(worldQuestion);
        }
        Challenge createdChallenge =  challengeRepo.save(challenge);
        List<Question> challengeQuestions = new ArrayList<Question>();
        List<Long> questionIds = createdChallenge.getQuestionIds();
        for (Long questionId : questionIds) {
            challengeQuestions.add(questionRepo.findById(questionId));
        }
        return challengeQuestions;
    }

    @Override
    public void delete(Challenge challenge) {
        challengeRepo.delete(challenge);
    }
}
