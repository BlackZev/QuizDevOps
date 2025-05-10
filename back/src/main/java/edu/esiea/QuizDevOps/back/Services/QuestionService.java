package edu.esiea.QuizDevOps.back.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.esiea.QuizDevOps.back.Entities.QuestionEntity;
import edu.esiea.QuizDevOps.back.Repositories.QuestionRepository;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository repo;

	public QuestionService(QuestionRepository repo) {
		this.repo = repo ;
	}
	
    public QuestionEntity create(QuestionEntity question) {
        return repo.save(question);
    }

    public List<QuestionEntity> readByQuizId(Long quizId) {
        return repo.findByQuizId(quizId);
    }
}