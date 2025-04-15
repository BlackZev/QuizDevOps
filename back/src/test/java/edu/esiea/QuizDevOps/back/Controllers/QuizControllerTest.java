package edu.esiea.QuizDevOps.back.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.esiea.QuizDevOps.back.Entities.QuizEntity;
import edu.esiea.QuizDevOps.back.Entities.UserEntity;
import edu.esiea.QuizDevOps.back.Enums.ETheme;
import edu.esiea.QuizDevOps.back.Services.QuizService;

@WebMvcTest(QuizController.class) 
public class QuizControllerTest {
	QuizEntity testQuiz;
	UserEntity creator;
	Long quizId;
	
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @MockitoBean
	private QuizService service;
    
    @BeforeEach
    void setUp() {
    	creator = new UserEntity("creator", "creator123!");
        testQuiz = new QuizEntity("Quiz", ETheme.TECHNOLOGY, creator);
    	quizId = 1L;
    }
    
    @Test
    void testCreate() throws Exception {
        when(service.create(any(QuizEntity.class))).thenReturn(testQuiz);        
        mockMvc.perform(post("/api/quizs/create")
        		.contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testQuiz)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(testQuiz.getName()))
                .andExpect(jsonPath("$.theme").value(testQuiz.getTheme().name()))
                .andExpect(jsonPath("$.creator.login").value(testQuiz.getCreator().getLogin()));        
        verify(service, times(1)).create(any(QuizEntity.class));
    }
    
    @Test
    void testReadAll() throws Exception {
        List<QuizEntity> quizList = List.of(testQuiz);
        when(service.readAll()).thenReturn(quizList);

        mockMvc.perform(get("/api/quizs/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(testQuiz.getName()))
                .andExpect(jsonPath("$[0].theme").value(testQuiz.getTheme().name()))
                .andExpect(jsonPath("$[0].creator.login").value(testQuiz.getCreator().getLogin()));
        verify(service, times(1)).readAll();
    }
    
    @Test
    void testReadById() throws Exception {
        when(service.readById(quizId)).thenReturn(testQuiz);

        mockMvc.perform(get("/api/quizs/id/{id}", quizId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(testQuiz.getName()))
                .andExpect(jsonPath("$.theme").value(testQuiz.getTheme().name()))
                .andExpect(jsonPath("$.creator.login").value(testQuiz.getCreator().getLogin()));
        verify(service, times(1)).readById(quizId);
    }

    @Test
    void testUpdate() throws Exception {
        QuizEntity updatedQuiz = new QuizEntity("Updated Quiz", ETheme.SCIENCE, creator);
        when(service.update(eq(quizId), any(QuizEntity.class))).thenReturn(updatedQuiz);

        mockMvc.perform(put("/api/quizs/update/{id}", quizId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testQuiz)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(updatedQuiz.getName()))
                .andExpect(jsonPath("$.theme").value(updatedQuiz.getTheme().name()))
                .andExpect(jsonPath("$.creator.login").value(updatedQuiz.getCreator().getLogin()));

        verify(service, times(1)).update(eq(1L), any(QuizEntity.class));
    }
    
	@Test
	void testDelete() throws Exception {
	    doNothing().when(service).deleteById(quizId);
	
	    mockMvc.perform(delete("/api/quizs/delete/{id}", quizId)
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isNoContent());
	
	    verify(service, times(1)).deleteById(quizId);
	}
}