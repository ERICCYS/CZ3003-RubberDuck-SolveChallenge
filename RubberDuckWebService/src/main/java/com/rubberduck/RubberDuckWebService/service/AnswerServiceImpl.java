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

    public void countAward(Answer answer, AnswerResultResponse answerResultResponse) {
        answerResultResponse.setCorrect(true);

        Long studentId = answer.getStudentId();
        Long questionId = answer.getQuestionId();
        Question question = questionRepo.findById(questionId);
        Student student = studentRepo.findById(studentId);

        List<Answer> previousAnswers = answerRepo.findByStudentIdAndQuestionIdAndMode(studentId, questionId, "Q");

        int mark;
        if (previousAnswers.size() >= 3) {
            answerResultResponse.setMessage("You have attempted too many times! Correct answer is " + question.getCorrectChoice());
        } else {
            if (previousAnswers.size() == 0) {
                mark = question.getAward();
            } else {
                for (Answer previousAnswer : previousAnswers) {
                    if (previousAnswer.isCorrect()) {
                        answerResultResponse.setMessage("You have answered this question correctly before! Correct answer is " + question.getCorrectChoice());
                        // don't save duplication correct answer for a question
                        return;
                    }
                }
                mark = (int) (question.getAward() * (1 - 0.33 * previousAnswers.size()));
            }
            answer.setReward(mark);
            answerResultResponse.setMark(mark);
            answerRepo.save(answer);
            answerResultResponse.setMessage("You got " + mark + " marks for this question");
            student.addMark(mark);
        }

        studentRepo.save(student);
    }

    @Override
    public AnswerResultResponse save(Answer answer) {
        AnswerResultResponse answerResultResponse = new AnswerResultResponse(0, false, "");

        if (answer == null) {
            answerResultResponse.setMessage("Answer Not Received");
            return answerResultResponse;
        }

        try {
            if (!checkAnswer(answer)) {
                answerResultResponse.setMessage("Wrong Answer");
                // only save incorrect answer in Q mode
                if (answer.getMode().equals("Q")) {
                    answerRepo.save(answer);
                }
            } else {
                // only save and count award for correct answer in Q mode
                if (answer.getMode().equals("Q")) {
                    countAward(answer, answerResultResponse);
                } else {
                    answerResultResponse.setCorrect(true);
                    answerResultResponse.setMessage("Correct!");
                }
            }
        } catch (NullPointerException e) {
            answerResultResponse.setMessage("Question Invalid");
        }

        return answerResultResponse;
    }

    @Override
    public void delete(Answer answer) {
        answerRepo.delete(answer);
    }

    public class AnswerResultResponse {
        private int mark;
        private boolean correct;
        private String message;

        public AnswerResultResponse(int mark, boolean correct, String message) {
            this.mark = mark;
            this.correct = correct;
            this.message = message;
        }

        public AnswerResultResponse() {
        }

        @Override
        public String toString() {
            return "AnswerResultResponse{" +
                    "mark=" + mark +
                    ", correct=" + correct +
                    ", message='" + message + '\'' +
                    '}';
        }

        public int getMark() {
            return mark;
        }

        public void setMark(int mark) {
            this.mark = mark;
        }

        public boolean isCorrect() {
            return correct;
        }

        public void setCorrect(boolean correct) {
            this.correct = correct;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
