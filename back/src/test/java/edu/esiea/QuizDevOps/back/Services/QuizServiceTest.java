package edu.esiea.QuizDevOps.back.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.esiea.QuizDevOps.back.Entities.QuizEntity;
import edu.esiea.QuizDevOps.back.Entities.UserEntity;
import edu.esiea.QuizDevOps.back.Enums.ETheme;
import edu.esiea.QuizDevOps.back.Repositories.QuizRepository;

@ExtendWith(MockitoExtension.class)
public class QuizServiceTest {
	UserEntity creator;
    QuizEntity testQuiz;
    QuizEntity testQuiz2;
    
    @Mock
    private QuizRepository repo;

    @InjectMocks
    private QuizService service;

    @BeforeEach
    void setUp() {
    	creator = new UserEntity("creator", "creator123!");
        testQuiz = new QuizEntity("Quiz", ETheme.TECHNOLOGY, creator);
        testQuiz2 = new QuizEntity("Quiz n°2", ETheme.ART, creator);
    }
    
    @Test
    void testCreate() {
        when(repo.save(testQuiz)).thenReturn(testQuiz);
        
        QuizEntity mockedQuiz = service.create(testQuiz);
        assertNotNull(mockedQuiz);
        assertEquals(testQuiz.getName(), mockedQuiz.getName());
        verify(repo, times(1)).save(testQuiz);       
    }
    
    @Test
    void testReadByAll() {
        List<QuizEntity> quizs = new ArrayList<QuizEntity>();
        quizs.add(testQuiz);
        quizs.add(testQuiz);

    	when(repo.findAll()).thenReturn(quizs);
        List<QuizEntity> result = service.readAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(repo, times(1)).findAll();
    }
    
    @Test
    void testReadById() {
        when(repo.findById(1L)).thenReturn(Optional.of(testQuiz));
        QuizEntity mockedQuiz = service.readById(1L);

        assertNotNull(testQuiz);
        assertEquals(testQuiz.getName(), mockedQuiz.getName());
        verify(repo, times(1)).findById(1L);
    }
    
    @Test
    void testUpdateById() {
        Long quizId = 1L;
        QuizEntity updatedQuiz = new QuizEntity("Updated Quiz", ETheme.ART, creator);

        when(repo.findById(quizId)).thenReturn(Optional.of(testQuiz));
        when(repo.save(any(QuizEntity.class))).thenReturn(updatedQuiz);

        QuizEntity result = service.update(quizId, updatedQuiz);

        assertNotNull(result);
        assertEquals("Updated Quiz", result.getName());
        assertEquals(ETheme.ART, result.getTheme());
        verify(repo).findById(quizId);
        verify(repo).save(any(QuizEntity.class));
    }
    
    @Test
    void testDeleteById() {
    	Long quizId = testQuiz.getId();
        when(repo.existsById(quizId)).thenReturn(true);
        doNothing().when(repo).deleteById(quizId);
        service.deleteById(quizId);
        
        verify(repo, times(1)).existsById(quizId);
        verify(repo, times(1)).deleteById(quizId);
    }
}