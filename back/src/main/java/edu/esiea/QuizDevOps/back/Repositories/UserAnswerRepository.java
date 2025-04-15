package edu.esiea.QuizDevOps.back.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.esiea.QuizDevOps.back.Entities.UserAnswerEntity;

public interface UserAnswerRepository extends JpaRepository<UserAnswerEntity, Long> { 
    List<UserAnswerEntity> findByUserIdAndQuestionId(Long userId, Long quizId);
}