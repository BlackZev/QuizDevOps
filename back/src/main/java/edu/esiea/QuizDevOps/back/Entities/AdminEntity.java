package edu.esiea.QuizDevOps.back.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AdminEntity {
	// Fields
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String login;
    private String password;
    
    
    // Constructors
    public AdminEntity() {}
    public AdminEntity(String login, String password) {
		this.login = login;
		this.password = password;
	}
    
    
	// Getters
	public Long getId() { return id; }
	
	public String getLogin() { return login; }
	
	public String getPassword() { return password; }
	
	
	// Setter
	public void setId(Long id) { this.id = id; }
	
	public void setLogin(String login) { this.login = login; }
	
	public void setPassword(String password) { this.password = password; }
}