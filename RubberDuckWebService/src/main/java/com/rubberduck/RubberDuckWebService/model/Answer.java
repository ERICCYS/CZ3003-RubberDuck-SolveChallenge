package com.rubberduck.RubberDuckWebService.model;

import javax.persistence.*;

@Entity
@Table(name = "Answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "STUDENT_ID")
    private Long studentId;

    @Column(name = "QUESTION_ID")
    private Long questionId;

    @Column(name = "CHOICE", nullable = false)
    private String choice;

    @Column(name = "IS_CORRECT", nullable = false)
    private boolean correct;

    @Column(name = "REWARD", nullable = false)
    private int reward;

    @Column(name = "MODE")
    private String mode;


    public Answer() {
    }


    public Answer(String choice, boolean correct, int reward, String mode) {
        this.choice = choice;
        this.correct = correct;
        this.reward = reward;
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", choice='" + choice + '\'' +
                ", correct=" + correct +
                ", reward='" + reward + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
