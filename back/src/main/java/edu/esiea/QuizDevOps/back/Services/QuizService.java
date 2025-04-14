package edu.esiea.QuizDevOps.back.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.esiea.QuizDevOps.back.Entities.QuizEntity;
import edu.esiea.QuizDevOps.back.Repositories.QuizRepository;

@Service
public class QuizService {
    @Autowired
    private QuizRepository repo;

	public QuizService(QuizRepository repo) {
		this.repo = repo ;
	}
	
    public QuizEntity create(QuizEntity quiz) {
        return repo.save(quiz);
    }
    
    public List<QuizEntity> readAll() {
        return repo.findAll();
    }
    
    public QuizEntity readById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public QuizEntity update(Long id, QuizEntity updateQuiz) {
    	QuizEntity quiz = repo.findById(id).orElseThrow(() -> new RuntimeException("quiz not found"));
    	quiz.setName(updateQuiz.getName());
    	quiz.setTheme(updateQuiz.getTheme());
    	quiz.setCreator(updateQuiz.getCreator());
    	return repo.save(quiz);
    }
    
    public void deleteById(Long id) {
		if (!repo.existsById(id)) {
			throw new RuntimeException("Quiz not found");
		}
		repo.deleteById(id);
    }
}