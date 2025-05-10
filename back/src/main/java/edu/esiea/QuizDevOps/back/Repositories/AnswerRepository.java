package edu.esiea.QuizDevOps.back.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.esiea.QuizDevOps.back.Entities.AnswerEntity;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
	List<AnswerEntity> findByQuestionId(Long questionId);
}