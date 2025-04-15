package edu.esiea.QuizDevOps.back.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class UserAnswerEntity {
	// Fields
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private QuestionEntity question;

    @ManyToOne
    private AnswerEntity selectedAnswer;

    
    // Constructors
    public UserAnswerEntity() {}

    public UserAnswerEntity(UserEntity user, QuestionEntity question, AnswerEntity selectedAnswer) {
        this.user = user;
        this.question = question;
        this.selectedAnswer = selectedAnswer;
    }

    
    // Getter
	public Long getId() {
		return id;
	}

	public UserEntity getUser() {
		return user;
	}

	public QuestionEntity getQuestion() {
		return question;
	}

	public AnswerEntity getSelectedAnswer() {
		return selectedAnswer;
	}
	
	
	// Setter
	public void setId(Long id) {
		this.id = id;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public void setQuestion(QuestionEntity question) {
		this.question = question;
	}

	public void setSelectedAnswer(AnswerEntity selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}
}