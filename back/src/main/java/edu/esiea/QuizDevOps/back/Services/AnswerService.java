package edu.esiea.QuizDevOps.back.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.esiea.QuizDevOps.back.Entities.AnswerEntity;
import edu.esiea.QuizDevOps.back.Repositories.AnswerRepository;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository repo;

	public AnswerService(AnswerRepository repo) {
		this.repo = repo ;
	}
	
    public AnswerEntity create(AnswerEntity answer) {
        return repo.save(answer);
    }
    
    public List<AnswerEntity> readByQuestionId(Long questionId) {
        return repo.findByQuestionId(questionId);
    }
}