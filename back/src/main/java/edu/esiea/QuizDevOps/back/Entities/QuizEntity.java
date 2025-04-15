package edu.esiea.QuizDevOps.back.Entities;

import edu.esiea.QuizDevOps.back.Enums.ETheme;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class QuizEntity {
	// Fields
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private ETheme theme;

    @ManyToOne
    private UserEntity creator;
    
    
    // Constructors
    public QuizEntity() {}
    public QuizEntity(String name, ETheme theme, UserEntity creator) {
		this.name = name;
		this.theme = theme;
		this.creator = creator;
	}

    
	// Getters
	public Long getId() { return id; }

	public String getName() { return name; }

	public ETheme getTheme() { return theme; }

	public UserEntity getCreator() { return creator; }

	
	// Setter
	public void setId(Long id) { this.id = id; }

	public void setName(String name) { this.name = name; }

	public void setTheme(ETheme theme) { this.theme = theme; }

	public void setCreator(UserEntity creator) { this.creator = creator; }
}