package edu.esiea.QuizDevOps.back.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import edu.esiea.QuizDevOps.back.Entities.QuestionEntity;
import edu.esiea.QuizDevOps.back.Entities.QuizEntity;
import edu.esiea.QuizDevOps.back.Entities.UserEntity;
import edu.esiea.QuizDevOps.back.Enums.ETheme;
import edu.esiea.QuizDevOps.back.Services.QuestionService;

@WebMvcTest(QuestionController.class) 
public class QuestionControllerTest {
	UserEntity creator;
	QuizEntity quiz;
	Long quizId;
	QuestionEntity testQuestion1;
	QuestionEntity testQuestion2;
		
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @MockitoBean
	private QuestionService service;
    
    @BeforeEach
    void setUp() {
    	creator = new UserEntity("creator", "creator123!");
        quiz = new QuizEntity("Quiz", ETheme.TECHNOLOGY, creator);
    	quizId = 1L;
    	quiz.setId(quizId);
    	
        testQuestion1 = new QuestionEntity("Question 1", quiz);
        testQuestion2 = new QuestionEntity("Question 2", quiz);
    }
    
    @Test
    void testCreate() throws Exception {
        when(service.create(any(QuestionEntity.class))).thenReturn(testQuestion1);        
        mockMvc.perform(post("/api/questions/create")
        		.contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testQuestion1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(testQuestion1.getContent()))
                .andExpect(jsonPath("$.quiz.name").value(testQuestion1.getQuiz().getName()))
                .andExpect(jsonPath("$.quiz.theme").value(testQuestion1.getQuiz().getTheme().name()))
                .andExpect(jsonPath("$.quiz.creator.login").value(testQuestion1.getQuiz().getCreator().getLogin()));        
        verify(service, times(1)).create(any(QuestionEntity.class));
    }
    
    @Test
    void testReadByQuizIdById() throws Exception {
        List<QuestionEntity> questions = List.of(testQuestion1, testQuestion2);

            when(service.readByQuizId(quizId)).thenReturn(questions);

            mockMvc.perform(get("/api/questions/quizId/{quizId}", quizId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].content").value("Question 1"))
                .andExpect(jsonPath("$[1].content").value("Question 2"));
    }
}