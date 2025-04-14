package edu.esiea.QuizDevOps.back.Entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class AnswerEntity {
	// Fields
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    private boolean isCorrect;
    
    @ManyToOne
    public QuestionEntity question;
    
    // Constructors
    public AnswerEntity() {}
    
	public AnswerEntity(String content, boolean isCorrect, QuestionEntity question) {
		this.content = content;
		this.isCorrect = isCorrect;
		this.question = question;
	}
	

	// Getters
	public String getContent() { return content; }
	
	public boolean isCorrect() { return isCorrect; }
	
	public QuestionEntity getQuestion() { return question; }
	
	
	// Setter
	public void setContent(String content) { this.content = content; }
	
	public void setCorrect(boolean isCorrect) { this.isCorrect = isCorrect; }
	
	public void setQuestion(QuestionEntity question) { this.question = question; }
}