package com.rubberduck.RubberDuckWebService.service;

import com.rubberduck.RubberDuckWebService.model.Answer;
import com.rubberduck.RubberDuckWebService.model.Question;
import com.rubberduck.RubberDuckWebService.model.Student;
import com.rubberduck.RubberDuckWebService.repo.AnswerRepo;
import com.rubberduck.RubberDuckWebService.repo.QuestionRepo;
import com.rubberduck.RubberDuckWebService.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerRepo answerRepo;

    @Autowired
    QuestionRepo questionRepo;

    @Autowired
    StudentRepo studentRepo;

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
        Student student = studentRepo.findById(studentId);

        List<Answer> previousAnswers = answerRepo.findByStudentIdAndQuestionIdAndMode(studentId, questionId, "Q");

        int mark;
        if (previousAnswers.size() >= 3) {
            awardMessage = "You have attempted too many times! Correct answer is " + question.getCorrectChoice();
        } else if (previousAnswers.size() == 0) {
            mark = question.getAward();
            answer.setReward(mark);
            answerRepo.save(answer);
            awardMessage = "You got " + mark + " marks for this question";
            student.addMark(mark);
        } else {
            for (Answer previousAnswer : previousAnswers) {
                if (previousAnswer.isCorrect()) {
                    awardMessage = "You have answered this question correctly before! Correct answer is " + question.getCorrectChoice();
                    return awardMessage;
                }
            }
            mark = (int) (question.getAward() * (1 - 0.33 * previousAnswers.size()));
            answer.setReward(mark);
            answerRepo.save(answer);
            awardMessage = "You got " + mark + " marks for this question";
            student.addMark(mark);
        }
        studentRepo.save(student);
        return awardMessage;
    }

    @Override
    public String save(Answer answer) {
        if (answer == null) {
            return "Answer Not Received";
        }

        String resultMessage;
        try {
            if (!checkAnswer(answer)) {
                resultMessage = "Wrong Answer";
            } else if (answer.getMode().equals("Q")) {
                resultMessage = countAward(answer);
            } else {
                resultMessage = "Correct!";
            }
        } catch (NullPointerException e) {
            return "Question Invalid";
        }

        return resultMessage;
    }

    @Override
    public void delete(Answer answer) {
        answerRepo.delete(answer);
    }
}
