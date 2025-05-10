package edu.esiea.QuizDevOps.back.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.esiea.QuizDevOps.back.Entities.AdminEntity;
import edu.esiea.QuizDevOps.back.Repositories.AdminRepository;

@Service
public class AdminService {
    @Autowired
    private AdminRepository repo;

	public AdminService(AdminRepository repo) {
		this.repo = repo ;
	}
	
    public AdminEntity create(AdminEntity admin) {
        return repo.save(admin);
    }
	
	public AdminEntity readById(Long id) {
		return repo.findById(id).orElse(null);
	}
}