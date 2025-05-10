package edu.esiea.QuizDevOps.back.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import edu.esiea.QuizDevOps.back.Entities.UserEntity;
import edu.esiea.QuizDevOps.back.Repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	UserEntity testUser;
    
    @Mock
    private UserRepository repo;

    @InjectMocks
    private UserService service;

    @BeforeEach
    void setUp() {
    	testUser = new UserEntity("user", "user123!");
    }
    
    @Test
    void testCreate() {
        when(repo.save(testUser)).thenReturn(testUser);
        
        UserEntity mockedUser = service.create(testUser);
        assertNotNull(mockedUser);
        assertEquals(testUser.getLogin(), mockedUser.getLogin());
        verify(repo, times(1)).save(testUser);       
    }
}