package br.com.lucccasdev.api.resources;

import br.com.lucccasdev.api.domain.User;
import br.com.lucccasdev.api.domain.dto.UserDTO;
import br.com.lucccasdev.api.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserResourceTest {

    public static final Integer ID = 1;
    public static final String NAME = "Vladmir";
    public static final String EMAIL = "vladmir@gmail.com";
    public static final String PASSWORD = "123";
    public static final int INDEX = 0;

    @InjectMocks
    private UserResource resource;

    @Mock
    private UserService service;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void createUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void delete() {
    }

    private void startUser (){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
       
    }
}