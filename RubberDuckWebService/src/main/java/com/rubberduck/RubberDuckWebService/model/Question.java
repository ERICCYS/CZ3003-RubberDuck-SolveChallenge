package com.rubberduck.RubberDuckWebService.model;

import javax.persistence.*;

@Entity
@Table(name = "Question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "CHARACTER_CHOICE", nullable = false)
    private String character;

    @Column(name = "WORLD", nullable = false)
    private String world;

    @Column(name = "SECTION", nullable = false)
    private String section;

    @Column(name = "LEVEL", nullable = false)
    private String level;

    @Column(name = "DIFFICULTY", nullable = false)
    private DifficultyEnum difficulty;

    @Column(name = "CHOICE_A", nullable = false)
    private String choiceA;

    @Column(name = "CHOICE_B", nullable = false)
    private String choiceB;

    @Column(name = "CHOICE_C", nullable = false)
    private String choiceC;

    @Column(name = "CHOICE_D", nullable = false)
    private String choiceD;

    @Column(name = "CORRECT_CHOICE", nullable = false)
    private String correctChoice;

    @Column(name = "AWARD", nullable = false)
    private int award = 15;


    public Question() {
    }

    public Question(String description, String character, String world, String section, String level, DifficultyEnum difficulty, String choiceA, String choiceB, String choiceC, String choiceD, String correctChoice, int award) {
        this.description = description;
        this.character = character;
        this.world = world;
        this.section = section;
        this.level = level;
        this.difficulty = difficulty;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
        this.choiceC = choiceC;
        this.choiceD = choiceD;
        this.correctChoice = correctChoice;
        this.award = award;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public DifficultyEnum getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyEnum difficulty) {
        this.difficulty = difficulty;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }

    public String getChoiceD() {
        return choiceD;
    }

    public void setChoiceD(String choiceD) {
        this.choiceD = choiceD;
    }

    public String getCorrectChoice() {
        return correctChoice;
    }

    public void setCorrectChoice(String correctChoice) {
        this.correctChoice = correctChoice;
    }

    public int getAward() {
        return award;
    }

    public void setAward(int award) {
        this.award = award;
    }
}
