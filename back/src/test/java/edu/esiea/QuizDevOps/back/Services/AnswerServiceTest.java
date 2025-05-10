package edu.esiea.QuizDevOps.back.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.esiea.QuizDevOps.back.Entities.AnswerEntity;
import edu.esiea.QuizDevOps.back.Entities.QuestionEntity;
import edu.esiea.QuizDevOps.back.Entities.QuizEntity;
import edu.esiea.QuizDevOps.back.Entities.UserEntity;
import edu.esiea.QuizDevOps.back.Enums.ETheme;
import edu.esiea.QuizDevOps.back.Repositories.AnswerRepository;

@ExtendWith(MockitoExtension.class)
public class AnswerServiceTest {
	UserEntity creator;
	QuizEntity quiz;
	QuestionEntity question;
	AnswerEntity testAnswer1;
	AnswerEntity testAnswer2;
    
    @Mock
    private AnswerRepository repo;

    @InjectMocks
    private AnswerService service;

    @BeforeEach
    void setUp() {
    	creator = new UserEntity("creator", "creator123!");
        quiz = new QuizEntity("Quiz", ETheme.TECHNOLOGY, creator);
        question = new QuestionEntity("Question 1", quiz);
        testAnswer1 = new AnswerEntity("Réponse A", true, question);
        testAnswer2 = new AnswerEntity("Réponse B", false, question);
    }
    
    @Test
    void testCreate() {
        when(repo.save(testAnswer1)).thenReturn(testAnswer1);
        
        AnswerEntity mockedQuestion = service.create(testAnswer1);
        assertNotNull(mockedQuestion);
        assertEquals(testAnswer1.getContent(), mockedQuestion.getContent());
        verify(repo, times(1)).save(testAnswer1);       
    }
    
    @Test
    void testReadByQuestionId() {
        Long questionId = 1L;
        List<AnswerEntity> answers = Arrays.asList(testAnswer1, testAnswer2);

        when(repo.findByQuestionId(questionId)).thenReturn(answers);

        List<AnswerEntity> result = service.readByQuestionId(questionId);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(repo, times(1)).findByQuestionId(questionId);
    }
}