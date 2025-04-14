package edu.esiea.QuizDevOps.back.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.esiea.QuizDevOps.back.Entities.AdminEntity;
import edu.esiea.QuizDevOps.back.Repositories.AdminRepository;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {
	AdminEntity testAdmin;
    
    @Mock
    private AdminRepository repo;

    @InjectMocks
    private AdminService service;

    @BeforeEach
    void setUp() {
    	testAdmin = new AdminEntity("admin", "admin123!");
    }
    
    @Test
    void testCreate() {
        when(repo.save(testAdmin)).thenReturn(testAdmin);
        
        AdminEntity mockedAdmin = service.create(testAdmin);
        assertNotNull(mockedAdmin);
        assertEquals(testAdmin.getLogin(), mockedAdmin.getLogin());
        verify(repo, times(1)).save(testAdmin);       
    }
    
    @Test
    void testReadById() {
        when(repo.findById(1L)).thenReturn(Optional.of(testAdmin));
        AdminEntity mockedAdmin = service.readById(1L);

        assertNotNull(testAdmin);
        assertEquals(testAdmin.getLogin(), mockedAdmin.getLogin());
        verify(repo, times(1)).findById(1L);
    }
}