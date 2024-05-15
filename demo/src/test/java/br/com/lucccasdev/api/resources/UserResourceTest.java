package br.com.lucccasdev.api.resources;

import br.com.lucccasdev.api.domain.User;
import br.com.lucccasdev.api.domain.dto.UserDTO;
import br.com.lucccasdev.api.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

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
    void findByIdWithSucess() {
        when(service.findById(anyInt())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = resource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId() );
        assertEquals(NAME, response.getBody().getName() );
        assertEquals(EMAIL, response.getBody().getEmail() );
        assertEquals(PASSWORD, response.getBody().getPassword());
    }

    @Test
    void findAllReturnAListOfDto() {
        when(service.findAll()).thenReturn(List.of(user));
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<List<UserDTO>> response = resource.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UserDTO.class, response.getBody().get(0).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NAME, response.getBody().get(INDEX).getName());
        assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
        assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());

    }

    @Test
    void createUserThenReturnCreated() {
        when(service.create(any())).thenReturn(user);

        ResponseEntity<UserDTO> response = resource.createUser(userDTO);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(null, response.getBody());

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