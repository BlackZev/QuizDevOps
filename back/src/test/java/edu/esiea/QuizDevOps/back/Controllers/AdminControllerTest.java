package edu.esiea.QuizDevOps.back.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import edu.esiea.QuizDevOps.back.Entities.AdminEntity;
import edu.esiea.QuizDevOps.back.Services.AdminService;

@WebMvcTest(AdminController.class) 
public class AdminControllerTest {
	AdminEntity testAdmin;
	
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @MockitoBean
	private AdminService service;
    
    @BeforeEach
    void setUp() {
    	testAdmin = new AdminEntity("admin", "admin123!");
    }
    
    @Test
    void testCreate() throws Exception {
        when(service.create(any(AdminEntity.class))).thenReturn(testAdmin);        
        mockMvc.perform(post("/api/admins/create")
        		.contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testAdmin)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value(testAdmin.getLogin()))
                .andExpect(jsonPath("$.password").value(testAdmin.getPassword()));
        
        verify(service, times(1)).create(any(AdminEntity.class));
    }
    
    @Test
    void testReadById() throws Exception {
        when(service.readById(1L)).thenReturn(testAdmin);

        mockMvc.perform(get("/api/admins/id/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value(testAdmin.getLogin()))
                .andExpect(jsonPath("$.password").value(testAdmin.getPassword()));

        verify(service, times(1)).readById(1L);
    }}