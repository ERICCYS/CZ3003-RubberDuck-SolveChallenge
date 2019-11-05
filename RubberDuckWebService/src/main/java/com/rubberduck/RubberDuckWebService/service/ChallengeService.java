package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.Challenge;
import com.rubberduck.RubberDuckWebService.model.Question;

import java.util.List;

public interface ChallengeService {

    Challenge findById(Long id);

    Challenge findByCreatorId(Long creatorId);

    void addQuestions(Challenge challenge);

    void increaseSuccess(Long challengeId);

    void increaseFail(Long challengeId);

    List<Question> save(Challenge challenge);

    void delete(Challenge challenge);
}
