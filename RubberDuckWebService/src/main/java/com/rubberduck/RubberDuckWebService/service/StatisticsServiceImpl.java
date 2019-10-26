package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.Answer;
import com.rubberduck.RubberDuckWebService.model.DifficultyEnum;
import com.rubberduck.RubberDuckWebService.model.Question;
import com.rubberduck.RubberDuckWebService.model.WorldQuestion;
import com.rubberduck.RubberDuckWebService.repo.AnswerRepo;
import com.rubberduck.RubberDuckWebService.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    QuestionRepo questionRepo;

    @Autowired
    AnswerRepo answerRepo;

    private class QuestionPerformance {
        private Long questionId;
        private String questionCharacter;
        private String questionWorld;
        private String questionSection;
        private String questionLevel;
        private DifficultyEnum questionDifficulty;
        private Integer questionFullScore;
        private Integer questionAttempts;
        private Double questionAverageScore;

        public QuestionPerformance(Question question, Integer questionAttempts, Double questionAverageScore) {
            this.questionId = question.getId();
            this.questionCharacter = question.getCharacter();
            this.questionWorld = question.getWorld();
            this.questionSection = question.getSection();
            this.questionLevel = question.getLevel();
            this.questionDifficulty = question.getDifficulty();
            this.questionFullScore = question.getAward();
            this.questionAttempts = questionAttempts;
            this.questionAverageScore = questionAverageScore;
        }

        @Override
        public String toString() {
            return "QuestionPerformance{" +
                    "questionId=" + questionId +
                    ", questionCharacter='" + questionCharacter + '\'' +
                    ", questionWorld='" + questionWorld + '\'' +
                    ", questionSection='" + questionSection + '\'' +
                    ", questionLevel='" + questionLevel + '\'' +
                    ", questionDifficulty=" + questionDifficulty +
                    ", questionFullScore=" + questionFullScore +
                    ", questionAttempts=" + questionAttempts +
                    ", questionAverageScore=" + questionAverageScore +
                    '}';
        }

        public Long getQuestionId() {
            return questionId;
        }

        public void setQuestionId(Long questionId) {
            this.questionId = questionId;
        }

        public String getQuestionCharacter() {
            return questionCharacter;
        }

        public void setQuestionCharacter(String questionCharacter) {
            this.questionCharacter = questionCharacter;
        }

        public String getQuestionWorld() {
            return questionWorld;
        }

        public void setQuestionWorld(String questionWorld) {
            this.questionWorld = questionWorld;
        }

        public String getQuestionSection() {
            return questionSection;
        }

        public void setQuestionSection(String questionSection) {
            this.questionSection = questionSection;
        }

        public String getQuestionLevel() {
            return questionLevel;
        }

        public void setQuestionLevel(String questionLevel) {
            this.questionLevel = questionLevel;
        }

        public DifficultyEnum getQuestionDifficulty() {
            return questionDifficulty;
        }

        public void setQuestionDifficulty(DifficultyEnum questionDifficulty) {
            this.questionDifficulty = questionDifficulty;
        }

        public Integer getQuestionFullScore() {
            return questionFullScore;
        }

        public void setQuestionFullScore(Integer questionFullScore) {
            this.questionFullScore = questionFullScore;
        }

        public Integer getQuestionAttempts() {
            return questionAttempts;
        }

        public void setQuestionAttempts(Integer questionAttempts) {
            this.questionAttempts = questionAttempts;
        }

        public Double getQuestionAverageScore() {
            return questionAverageScore;
        }

        public void setQuestionAverageScore(Double questionAverageScore) {
            this.questionAverageScore = questionAverageScore;
        }
    }

    private class SectionPerformance {
        private String world;
        private String section;
        private Integer totalAttempts;
        private Double averageScore;

        public SectionPerformance(String world, String section, Integer totalAttempts, Double averageScore) {
            this.world = world;
            this.section = section;
            this.totalAttempts = totalAttempts;
            this.averageScore = averageScore;
        }

        @Override
        public String toString() {
            return "SectionPerformance{" +
                    "world='" + world + '\'' +
                    ", section='" + section + '\'' +
                    ", totalAttempts=" + totalAttempts +
                    ", averageScore=" + averageScore +
                    '}';
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

        public Integer getTotalAttempts() {
            return totalAttempts;
        }

        public void setTotalAttempts(Integer totalAttempts) {
            this.totalAttempts = totalAttempts;
        }

        public void addAttempt(Integer attempts) {
            this.totalAttempts += attempts;
        }

        public Double getAverageScore() {
            return averageScore;
        }

        public void setAverageScore(Double averageScore) {
            this.averageScore = averageScore;
        }

        public void addScore(Double averageScore) {
            this.averageScore += averageScore;
        }
    }

    private class WorldPerformance {
        private String world;
        private Integer totalAttempts;
        private Double averageScore;

        public WorldPerformance(String world, Integer totalAttempts, Double averageScore) {
            this.world = world;
            this.totalAttempts = totalAttempts;
            this.averageScore = averageScore;
        }

        @Override
        public String toString() {
            return "WorldPerformance{" +
                    "world='" + world + '\'' +
                    ", totalAttempts=" + totalAttempts +
                    ", averageScore=" + averageScore +
                    '}';
        }

        public String getWorld() {
            return world;
        }

        public void setWorld(String world) {
            this.world = world;
        }

        public Integer getTotalAttempts() {
            return totalAttempts;
        }

        public void setTotalAttempts(Integer totalAttempts) {
            this.totalAttempts = totalAttempts;
        }

        public void addAttempt(Integer attempts) {
            this.totalAttempts += attempts;
        }

        public Double getAverageScore() {
            return averageScore;
        }

        public void setAverageScore(Double averageScore) {
            this.averageScore = averageScore;
        }

        public void addScore(Double averageScore) {
            this.averageScore += averageScore;
        }
    }

    @Override
    public List<Object> getQuestionPerformance(String character) {
        List<Question> questions = questionRepo.findByCharacter(character);
        List<Object> questionPerformances = new ArrayList<>();
        for (Question question : questions) {
            // get the number of attempts
            List<Answer> answers = answerRepo.findByQuestionIdAndMode(question.getId(), "Q");
            Integer questionAttempts = answers.size();
            Double questionAverageScore = 0d;
            if (questionAttempts != 0) {
                for (Answer answer : answers) {
                    questionAverageScore += answer.getReward();
                }
                questionAverageScore = questionAverageScore/questionAttempts;
            }
            QuestionPerformance questionPerformance = new QuestionPerformance(question, questionAttempts, questionAverageScore);
            questionPerformances.add(questionPerformance);
        }
        return questionPerformances;
    }

    @Override
    public List<Object> getSectionPerformance(String character, Map<String, List<String>> worldSections) {
        List<Object> questionPerformances = this.getQuestionPerformance(character);
        List<Object> sectionPerformances = new ArrayList<>();

        for (Map.Entry<String, List<String>> worldSection : worldSections.entrySet()) {
            String world = worldSection.getKey();
            List<String> sections = worldSection.getValue();
            for (String section : sections) {
                SectionPerformance sectionPerformance = new SectionPerformance(world, section, 0,0d);
                for (Object questionPerformance : questionPerformances) {
                    if (((QuestionPerformance) questionPerformance).getQuestionWorld().equals(world) && ((QuestionPerformance) questionPerformance).getQuestionSection().equals(section)) {
                        sectionPerformance.addAttempt(((QuestionPerformance) questionPerformance).getQuestionAttempts());
                        sectionPerformance.addScore(((QuestionPerformance) questionPerformance).getQuestionAverageScore());
                    }
                }
                sectionPerformances.add(sectionPerformance);
            }

        }
        return sectionPerformances;
    }

    @Override
    public List<Object> getWorldPerformance(String character, List<String> worlds) {
        List<Object> questionPerformances = this.getQuestionPerformance(character);
        List<Object> worldPerformances = new ArrayList<>();

        for (String world : worlds) {
            WorldPerformance worldPerformance = new WorldPerformance(world, 0,0d);
            for (Object questionPerformance : questionPerformances) {
                if (((QuestionPerformance) questionPerformance).getQuestionWorld().equals(world)) {
                    worldPerformance.addAttempt(((QuestionPerformance) questionPerformance).getQuestionAttempts());
                    worldPerformance.addScore(((QuestionPerformance) questionPerformance).getQuestionAverageScore());
                }
            }
            worldPerformances.add(worldPerformance);
        }
        return worldPerformances;
    }
}
