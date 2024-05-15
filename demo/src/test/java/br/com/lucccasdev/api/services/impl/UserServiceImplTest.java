package br.com.lucccasdev.api.services.impl;

import br.com.lucccasdev.api.domain.User;
import br.com.lucccasdev.api.domain.dto.UserDTO;
import br.com.lucccasdev.api.repositories.UserRepository;
import br.com.lucccasdev.api.services.exceptions.DataIntegratyViolationException;
import br.com.lucccasdev.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Vladmir";
    public static final String EMAIL = "vladmir@gmail.com";
    public static final String PASSWORD = "123";
    public static final int INDEX = 0;
    @InjectMocks // cria uma instancia real
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // ele ira inicializar os os mockitos desta classe
        startUser();
    }

    @Test
    void findByIdReturnAnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);

        User response = service.findById(ID);

        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void findByIdReturnAnObjectNotFoundException() {


        try {
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("User not found", ex.getMessage());
        }


    }

    @Test
    void findAllShouldReturnAnListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(user));

        List<User> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());

    }

    @Test
    void createShouldReturnSucess() {
        when(repository.save(any())).thenReturn(user);

        User response = service.create(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void createShouldReturnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(4);
            service.create(userDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("E-mail já cadastrado no sistema", ex.getMessage());
        }

    }

    @Test
    void updateShouldReturnSucess() {
        when(repository.save(any())).thenReturn(user);

        User response = service.update(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void updateShouldReturnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(4);
            service.update(userDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("E-mail já cadastrado no sistema", ex.getMessage());
        }

    }


    @Test
    void deleteWithSucess() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);
            doNothing().when(repository).deleteById(anyInt());
            service.delete(ID);
            verify(repository, times(1)).deleteById((anyInt())); // verificar se o metodo deleteById foi chamado mais de uma vez
        }


    @Test
    void deleteWithNoSucess() {
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException("User not found"));

        try {
            service.delete(ID);
        }catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("User not found", ex.getMessage());
        }
        }


    private void startUser (){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }

}
