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
    public List<Challenge> findByCreatorId(Long creatorId) {
        return challengeRepo.findByCreatorId(creatorId);
    }

    @Override
    public void addQuestions(Challenge challenge) {
        String character = challenge.getCharacter();
        List<WorldQuestion> worldQuestions = challenge.getWorldQuestion();
        List<Long> questionIds = new ArrayList<>();

        for (WorldQuestion worldQuestion : worldQuestions) {
            String world = worldQuestion.getWorld();
            Map<DifficultyEnum, Integer> questionCount = worldQuestion.getQuestionCount();
            for (Map.Entry<DifficultyEnum, Integer> questionInfo : questionCount.entrySet()) {
                System.out.println(character + " " + world + " " + questionInfo.getKey().toString() + " " + questionInfo.getValue());
                List<Question> sampledQuestion = questionRepo.samplingQuestion(character, world, questionInfo.getKey().toString(), questionInfo.getValue());
                for (Question question : sampledQuestion) {
                    questionIds.add(question.getId());
                }
            }
        }
        challenge.setQuestionIds(questionIds);
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
