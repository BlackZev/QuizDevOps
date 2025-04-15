package edu.esiea.QuizDevOps.back.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.esiea.QuizDevOps.back.Entities.UserEntity;
import edu.esiea.QuizDevOps.back.Services.UserService;

@WebMvcTest(UserController.class) 
public class UserControllerTest {
	UserEntity testUser;
	
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @MockitoBean
	private UserService service;
    
    @BeforeEach
    void setUp() {
    	testUser = new UserEntity("user", "user123!");
    }
    
    @Test
    void testCreate() throws Exception {
        when(service.create(any(UserEntity.class))).thenReturn(testUser);        
        mockMvc.perform(post("/api/users/create")
        		.contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value(testUser.getLogin()))
                .andExpect(jsonPath("$.password").value(testUser.getPassword()));
        
        verify(service, times(1)).create(any(UserEntity.class));
    }
    
    void testReadById() throws Exception {
        when(service.create(any(UserEntity.class))).thenReturn(testUser);        
        mockMvc.perform(post("/api/users/create")
        		.contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value(testUser.getLogin()))
                .andExpect(jsonPath("$.password").value(testUser.getPassword()));
        
        verify(service, times(1)).create(any(UserEntity.class));
    }
}