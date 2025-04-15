package edu.esiea.QuizDevOps.back.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.esiea.QuizDevOps.back.Entities.UserEntity;
import edu.esiea.QuizDevOps.back.Repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

	public UserService(UserRepository repo) {
		this.repo = repo ;
	}
	
    public UserEntity create(UserEntity user) {
        return repo.save(user);
    }
}