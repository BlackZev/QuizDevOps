package edu.esiea.QuizDevOps.back.Entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class AnswerEntity {
	// Fields
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    private boolean isCorrect;
    
    // Constructors
    public AnswerEntity() {}
    
	public AnswerEntity(String content, boolean isCorrect, QuestionEntity question) {
		this.content = content;
		this.isCorrect = isCorrect;
	}


	// Getters
	public String getContent() { return content; }
	
	public boolean isCorrect() { return isCorrect; }
		
	
	// Setter
	public void setContent(String content) { this.content = content; }
	
	public void setCorrect(boolean isCorrect) { this.isCorrect = isCorrect; }
}