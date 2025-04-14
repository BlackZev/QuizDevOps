package edu.esiea.QuizDevOps.back.Entities;

import java.util.List;

import edu.esiea.QuizDevOps.back.Enums.ETheme;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

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

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<QuestionEntity> questions;
    
    
    // Constructors
    public QuizEntity() {}
    public QuizEntity(String name, ETheme theme, UserEntity creator, List<QuestionEntity> questions) {
		this.name = name;
		this.theme = theme;
		this.creator = creator;
		this.questions = questions;
	}

    
	// Getters
	public Long getId() { return id; }

	public String getName() { return name; }

	public ETheme getTheme() { return theme; }

	public UserEntity getCreator() { return creator; }

	public List<QuestionEntity> getQuestions() { return questions; }

	
	// Setter
	public void setId(Long id) { this.id = id; }

	public void setName(String name) { this.name = name; }

	public void setTheme(ETheme theme) { this.theme = theme; }

	public void setCreator(UserEntity creator) { this.creator = creator; }

	public void setQuestions(List<QuestionEntity> questions) { this.questions = questions; }
}