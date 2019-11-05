package com.rubberduck.RubberDuckWebService.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ChallengeRecord")
public class ChallengeRecord {

    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CHALLENGER_ID", nullable = false)
    private Long challengerId;

    @Column(name = "CHALLENGE_ID", nullable = false)
    private Long challengeId;

    @Column(name = "ANSWER_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date answerTime;

    @Column(name = "CORRECT_ANSWER_COUNT", nullable = false)
    private int correctAnswerCount;

    @Column(name = "IS_SUCCESS", nullable = false)
    private boolean success;

    public ChallengeRecord(Long challengerId, Long challengeId, Date answerTime, int correctAnswerCount, boolean success) {
        this.challengerId = challengerId;
        this.challengeId = challengeId;
        this.answerTime = answerTime;
        this.correctAnswerCount = correctAnswerCount;
        this.success = success;
    }

    public ChallengeRecord() {
    }

    public Long getId() {
        return id;
    }

    public Long getChallengerId() {
        return challengerId;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public int getCorrectAnswerCount() {
        return correctAnswerCount;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setCorrectAnswerCount(int correctAnswerCount) {
        this.correctAnswerCount = correctAnswerCount;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
