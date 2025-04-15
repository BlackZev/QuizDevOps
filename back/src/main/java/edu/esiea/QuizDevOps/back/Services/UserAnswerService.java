package edu.esiea.QuizDevOps.back.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.esiea.QuizDevOps.back.Entities.AnswerEntity;
import edu.esiea.QuizDevOps.back.Entities.QuestionEntity;
import edu.esiea.QuizDevOps.back.Entities.UserAnswerEntity;
import edu.esiea.QuizDevOps.back.Entities.UserEntity;
import edu.esiea.QuizDevOps.back.Repositories.AnswerRepository;
import edu.esiea.QuizDevOps.back.Repositories.QuestionRepository;
import edu.esiea.QuizDevOps.back.Repositories.UserAnswerRepository;

@Service
public class UserAnswerService {
    @Autowired
    private UserAnswerRepository userAnswerRepo;

    @Autowired
    private AnswerRepository answerRepo;

    @Autowired
    private QuestionRepository questionRepo;

    public UserAnswerEntity saveUserAnswer(Long userId, Long questionId, Long answerId) {
        QuestionEntity question = questionRepo.findById(questionId).orElseThrow(() -> new RuntimeException("Question not found"));
        AnswerEntity answer = answerRepo.findById(answerId).orElseThrow(() -> new RuntimeException("Answer not found"));
        UserEntity user = new UserEntity();
        user.setId(userId);

        return userAnswerRepo.save(new UserAnswerEntity(user, question, answer));
    }

    public int calculateScore(Long userId, Long quizId) {
        List<UserAnswerEntity> answers = userAnswerRepo.findByUserIdAndQuestionId(userId, quizId);

        int score = 0;
        for (UserAnswerEntity answer : answers) {
            if (answer.getSelectedAnswer().isCorrect()) { score++; }
        }

        return score;
    }
}