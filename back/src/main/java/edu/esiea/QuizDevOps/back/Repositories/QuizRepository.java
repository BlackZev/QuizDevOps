package edu.esiea.QuizDevOps.back.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.esiea.QuizDevOps.back.Entities.QuizEntity;

public interface QuizRepository extends JpaRepository<QuizEntity, Long> { }