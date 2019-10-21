package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.Challenge;

import java.util.List;

public interface ChallengeService {

    Challenge findById(Long id);

    List<Challenge> findByCreatorId(Long creatorId);

    void addQuestions(Challenge challenge);

    Challenge save(Challenge challenge);

    void delete(Challenge challenge);

    // need to generate the challenge (sampling question from the world

}
