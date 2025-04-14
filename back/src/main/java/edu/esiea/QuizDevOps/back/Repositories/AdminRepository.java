package edu.esiea.QuizDevOps.back.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.esiea.QuizDevOps.back.Entities.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> { }