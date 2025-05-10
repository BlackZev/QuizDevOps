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

import edu.esiea.QuizDevOps.back.Entities.AnswerEntity;
import edu.esiea.QuizDevOps.back.Entities.QuestionEntity;
import edu.esiea.QuizDevOps.back.Entities.QuizEntity;
import edu.esiea.QuizDevOps.back.Entities.UserEntity;
import edu.esiea.QuizDevOps.back.Enums.ETheme;
import edu.esiea.QuizDevOps.back.Services.AnswerService;

@WebMvcTest(AnswerController.class) 
public class AnswerControllerTest {
	UserEntity creator;
	QuizEntity quiz;
	QuestionEntity question;
	Long questionId;
	AnswerEntity testAnswer1;
	AnswerEntity testAnswer2;
		
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @MockitoBean
	private AnswerService service;
    
    @BeforeEach
    void setUp() {
    	creator = new UserEntity("creator", "creator123!");
        quiz = new QuizEntity("Quiz", ETheme.TECHNOLOGY, creator);
        question = new QuestionEntity("Question 1", quiz);
        questionId = 42L;
        question.setId(questionId);
        testAnswer1 = new AnswerEntity("Réponse A", true, question);
        testAnswer2 = new AnswerEntity("Réponse B", false, question);
    }
    
    @Test
    void testCreate() throws Exception {
        when(service.create(any(AnswerEntity.class))).thenReturn(testAnswer1);        
        mockMvc.perform(post("/api/answers/create")
        		.contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testAnswer1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(testAnswer1.getContent()))
                .andExpect(jsonPath("$.question.content").value(testAnswer1.getQuestion().getContent()))
                .andExpect(jsonPath("$.question.quiz.name").value(testAnswer1.getQuestion().getQuiz().getName()))
                .andExpect(jsonPath("$.question.quiz.theme").value(testAnswer1.getQuestion().getQuiz().getTheme().name()))
                .andExpect(jsonPath("$.question.quiz.creator.login").value(testAnswer1.getQuestion().getQuiz().getCreator().getLogin()));        
        verify(service, times(1)).create(any(AnswerEntity.class));
    }
    
    @Test
    void testReadByQuestionId() throws Exception {
        List<AnswerEntity> answers = List.of(testAnswer1, testAnswer2);

        when(service.readByQuestionId(questionId)).thenReturn(answers);

        mockMvc.perform(get("/api/answers/questionId/{questionId}", questionId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].content").value("Réponse A"))
            .andExpect(jsonPath("$[1].content").value("Réponse B"));
    }
}