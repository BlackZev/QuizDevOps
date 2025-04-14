package edu.esiea.QuizDevOps.back.Entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class QuestionEntity {
	// Fields
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;

    @ManyToOne
    private QuizEntity quiz;
    
    // Constructors
    public QuestionEntity() {}

	public QuestionEntity(String content, QuizEntity quiz) {
		this.content = content;
		this.quiz = quiz;
	}


	// Getters
	public Long getId() { return id; }

	public String getContent() { return content; }
	
	public QuizEntity getQuiz() { return quiz; }


	// Setter
	public void setId(Long id) { this.id = id; }

	public void setContent(String content) { this.content = content; }
	
	public void setQuiz(QuizEntity quiz) { this.quiz = quiz; }

}