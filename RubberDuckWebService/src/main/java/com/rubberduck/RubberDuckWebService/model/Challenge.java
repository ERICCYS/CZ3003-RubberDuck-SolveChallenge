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

    @ElementCollection
    private List<Long> questionIds;

    @Column(name = "CREATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Column(name = "QUESTION_COUNT", nullable = false)
    private int questionCount;

    @Column(name = "EASY_COUNT", nullable = false)
    private int easyQnCount;

    @Column(name = "MEDIUM_COUNT", nullable = false)
    private int mediumQnCount;

    @Column(name = "HARD_COUNT", nullable = false)
    private int hardQnCount;

    @Column(name = "SUCCESS_COUNT", nullable = false)
    private int successCount;

    @Column(name = "FAILURE_COUNT", nullable = false)
    private int failureCount;

    public Challenge() {
    }


    public Challenge(Long creatorId, Date createTime, int questionCount, int easyQnCount, int mediumQnCount, int hardQnCount) {
        this.creatorId = creatorId;
        this.createTime = createTime;
        this.questionCount = questionCount;
        this.easyQnCount = easyQnCount;
        this.mediumQnCount = mediumQnCount;
        this.hardQnCount = hardQnCount;
        this.successCount = 0;
        this.failureCount = 0;
    }


    @Override
    public String toString() {
        return "Challenge{" +
                "id=" + id +
                ", creatorId=" + creatorId +
                ", questionIds=" + questionIds +
                ", createTime=" + createTime +
                ", questionCount=" + questionCount +
                ", easyQnCount=" + easyQnCount +
                ", mediumQnCount=" + mediumQnCount +
                ", hardQnCount=" + hardQnCount +
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

    public List<Long> getQuestionIds() {
        return questionIds;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public int getEasyQnCount() {
        return easyQnCount;
    }

    public int getMediumQnCount() {
        return mediumQnCount;
    }

    public int getHardQnCount() {
        return hardQnCount;
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
