package edu.esiea.QuizDevOps.back.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.esiea.QuizDevOps.back.Entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> { }