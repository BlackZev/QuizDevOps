package edu.esiea.QuizDevOps.back.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.esiea.QuizDevOps.back.Entities.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> { 
	List<QuestionEntity> findByQuizId(Long quizId);
}