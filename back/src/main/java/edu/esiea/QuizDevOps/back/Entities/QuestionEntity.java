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

    
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<AnswerEntity> answers;
    
    @ManyToOne
    private QuizEntity quiz;
    
    // Constructors
    public QuestionEntity() {}

	public QuestionEntity(String content, QuizEntity quiz, List<AnswerEntity> answers) {
		this.content = content;
		this.quiz = quiz;
		this.answers = answers;
	}


	// Getters
	public Long getId() { return id; }

	public String getContent() { return content; }
	
	public QuizEntity getQuiz() { return quiz; }

	public List<AnswerEntity> getAnswers() { return answers; }


	// Setter
	public void setId(Long id) { this.id = id; }

	public void setContent(String content) { this.content = content; }

	public void setAnswers(List<AnswerEntity> answers) { this.answers = answers; }
	
	public void setQuiz(QuizEntity quiz) { this.quiz = quiz; }

}