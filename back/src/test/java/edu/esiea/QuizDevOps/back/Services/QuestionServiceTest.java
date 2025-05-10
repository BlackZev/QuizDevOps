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

import edu.esiea.QuizDevOps.back.Entities.QuestionEntity;
import edu.esiea.QuizDevOps.back.Entities.QuizEntity;
import edu.esiea.QuizDevOps.back.Entities.UserEntity;
import edu.esiea.QuizDevOps.back.Enums.ETheme;
import edu.esiea.QuizDevOps.back.Repositories.QuestionRepository;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {
	UserEntity creator;
	QuizEntity quiz;
	QuestionEntity testQuestion1;
	QuestionEntity testQuestion2;
    
    @Mock
    private QuestionRepository repo;

    @InjectMocks
    private QuestionService service;

    @BeforeEach
    void setUp() {
    	creator = new UserEntity("creator", "creator123!");
        quiz = new QuizEntity("Quiz", ETheme.TECHNOLOGY, creator);
        testQuestion1 = new QuestionEntity("Question 1", quiz);
        testQuestion1 = new QuestionEntity("Question 2", quiz);
    }
    
    @Test
    void testCreate() {
        when(repo.save(testQuestion1)).thenReturn(testQuestion1);
        
        QuestionEntity mockedQuestion = service.create(testQuestion1);
        assertNotNull(mockedQuestion);
        assertEquals(testQuestion1.getContent(), mockedQuestion.getContent());
        verify(repo, times(1)).save(testQuestion1);       
    }
    
    @Test
    void testReadByQuizId() { 
        Long quizId = 1L;
        List<QuestionEntity> questions = Arrays.asList(
            new QuestionEntity("Question 1", quiz),
            new QuestionEntity("Question 2", quiz)
        );

        when(repo.findByQuizId(quizId)).thenReturn(questions);

        List<QuestionEntity> result = service.readByQuizId(quizId);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(repo, times(1)).findByQuizId(quizId);
    }
}