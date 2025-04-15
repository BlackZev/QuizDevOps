package edu.esiea.QuizDevOps.back.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.esiea.QuizDevOps.back.Entities.AnswerEntity;
import edu.esiea.QuizDevOps.back.Entities.QuestionEntity;
import edu.esiea.QuizDevOps.back.Entities.UserAnswerEntity;
import edu.esiea.QuizDevOps.back.Entities.UserEntity;
import edu.esiea.QuizDevOps.back.Services.UserAnswerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserAnswerController.class)
public class UserAnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserAnswerService userAnswerService;

    private UserAnswerEntity mockUserAnswer;

    @BeforeEach
    void setUp() {
        UserEntity user = new UserEntity();
        user.setId(1L);

        QuestionEntity question = new QuestionEntity();
        question.setId(2L);

        AnswerEntity answer = new AnswerEntity();
        answer.setId(3L);
        answer.setCorrect(true);

        mockUserAnswer = new UserAnswerEntity(user, question, answer);
        mockUserAnswer.setId(10L);
    }

    @Test
    void testSubmitAnswer() throws Exception {
        Long userId = 1L;
        Long questionId = 2L;
        Long answerId = 3L;

        when(userAnswerService.saveUserAnswer(userId, questionId, answerId)).thenReturn(mockUserAnswer);

        mockMvc.perform(post("/api/users/submit/1/2/3")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.user.id").value(userId))
            .andExpect(jsonPath("$.question.id").value(questionId))
            .andExpect(jsonPath("$.selectedAnswer.id").value(answerId));
    }

    @Test
    void testGetScore() throws Exception {
        Long userId = 1L;
        Long quizId = 99L;
        int expectedScore = 5;

        when(userAnswerService.calculateScore(userId, quizId)).thenReturn(expectedScore);

        mockMvc.perform(get("/api/users/score/1/99"))
	        .andExpect(status().isOk())
	        .andExpect(content().string("5"));
    }
}