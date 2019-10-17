package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.Answer;
import com.rubberduck.RubberDuckWebService.model.Question;
import com.rubberduck.RubberDuckWebService.repo.AnswerRepo;
import com.rubberduck.RubberDuckWebService.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerRepo answerRepo;

    @Autowired
    QuestionRepo questionRepo;

    @Override
    public Answer findById(Long id) {
        return answerRepo.findById(id);
    }

    @Override
    public List<Answer> findByStudentId(Long studentId) {
        return answerRepo.findByStudentId(studentId);
    }

    @Override
    public List<Answer> findByQuestionId(Long questionId) {
        return answerRepo.findByQuestionId(questionId);
    }

    @Override
    public boolean checkAnswer(Answer answer) throws NullPointerException {
        // Get the corresponding question
        Long questionId = answer.getQuestionId();
        Question question = questionRepo.findById(questionId);
        boolean isCorrect = question.getCorrectChoice().equals(answer.getChoice());
        answer.setCorrect(isCorrect);
        return isCorrect;
    }

    @Override
    public String countAward(Answer answer) {
        String awardMessage;

        Long studentId = answer.getStudentId();
        Long questionId = answer.getQuestionId();
        Question question = questionRepo.findById(questionId);

        // check how many times
        List<Answer> previousAnswers = answerRepo.findByStudentIdAndQuestionIdAndMode(studentId, questionId, "Q");

        if (previousAnswers.size() >= 3) {
            awardMessage = "You have attempted too many times!";
        } else if (previousAnswers.size() == 0) {
            int mark = question.getAward();
            answer.setReward(mark);
            awardMessage = "You got " + mark + " marks for this question";
        } else {
            // count if there is successful answers
            for (Answer previousAnswer : previousAnswers) {
                if (previousAnswer.isCorrect()) {
                    awardMessage = "You have answered this question correctly before!";
                    return awardMessage;
                }
            }
            int mark = (int) (question.getAward() * (1 - 0.3 * previousAnswers.size()));
            answer.setReward(mark);
            awardMessage = "You got " + mark + " marks for this question";
        }

        return awardMessage;
    }

    @Override
    public String save(Answer answer) {
        // validate answer
        if (answer == null) {
            return "Answer Not Received";
        }

        String resultMessage;
        try {
            if (!checkAnswer(answer)) {
                resultMessage = "Wrong Answer";
            } else if (answer.getMode().equals("Q")){
                resultMessage = countAward(answer);
            } else {
                resultMessage = "Correct!";
            }
        } catch (NullPointerException e) {
            return "Question Invalid";
        }
        answerRepo.save(answer);
        return resultMessage;
    }

    @Override
    public void delete(Answer answer) {
        answerRepo.delete(answer);
    }
}
