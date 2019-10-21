package com.rubberduck.RubberDuckWebService.model;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "WorldQuestion")
public class WorldQuestion {

    @Id
    @Column(name = "WORLD_QN_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "world")
    private String world;

    @ElementCollection
    private Map<DifficultyEnum, Integer> questionCount;

    public WorldQuestion() {
    }

    public WorldQuestion(String world, Map<DifficultyEnum, Integer> questionCount) {
        this.world = world;
        this.questionCount = questionCount;
    }

    public Long getId() {
        return id;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public Map<DifficultyEnum, Integer> getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Map<DifficultyEnum, Integer> questionCount) {
        this.questionCount = questionCount;
    }
}
