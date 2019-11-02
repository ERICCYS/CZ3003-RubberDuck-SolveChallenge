package com.rubberduck.RubberDuckWebService.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Challenge")
public class Challenge {

    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CREATOR_ID", nullable = false)
    private Long creatorId;

    @Column(name = "CHARACTER_CHOICE", nullable = false)
    private String character;

    @ElementCollection
    private List<Long> questionIds;

    @ElementCollection

    private List<WorldQuestion> worldQuestion;

    @Column(name = "CREATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Column(name = "SUCCESS_COUNT", nullable = false)
    private int successCount;

    @Column(name = "FAILURE_COUNT", nullable = false)
    private int failureCount;

    public Challenge() {
    }


    public Challenge(Long creatorId, String character, List<Long> questionIds, List<WorldQuestion> worldQuestion, Date createTime, int successCount, int failureCount) {
        this.creatorId = creatorId;
        this.character = character;
        this.questionIds = questionIds;
        this.worldQuestion = worldQuestion;
        this.createTime = createTime;
        this.successCount = successCount;
        this.failureCount = failureCount;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "id=" + id +
                ", creatorId=" + creatorId +
                ", character='" + character + '\'' +
                ", questionIds=" + questionIds +
                ", worldQuestion=" + worldQuestion +
                ", createTime=" + createTime +
                ", successCount=" + successCount +
                ", failureCount=" + failureCount +
                '}';
    }

    public Long getId() {
        return id;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public String getCharacter() {
        return character;
    }

    public List<Long> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Long> questionIds) {
        this.questionIds = questionIds;
    }

    public List<WorldQuestion> getWorldQuestion() {
        return worldQuestion;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void increaseSuccessCount(int successCount) {
        this.successCount += 1;
    }

    public int getFailureCount() {
        return failureCount;
    }

    public void increaseFailureCount(int failureCount) {
        this.failureCount += 1;
    }
}
