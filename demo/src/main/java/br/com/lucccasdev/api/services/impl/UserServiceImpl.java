package br.com.lucccasdev.api.services.impl;

import br.com.lucccasdev.api.domain.User;
import br.com.lucccasdev.api.domain.dto.UserDTO;
import br.com.lucccasdev.api.repositories.UserRepository;
import br.com.lucccasdev.api.services.UserService;
import br.com.lucccasdev.api.services.exceptions.DataIntegratyViolationException;
import br.com.lucccasdev.api.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("User not found"));
    }

    @Override
    public List<User> findAll(){
        return repository.findAll();
    }

    @Override
    public User create(UserDTO obj) {
        findByEmail(obj);
        User user = mapper.map(obj, User.class);
        return repository.save(user);
    }

    @Override
    public User update(UserDTO obj) {
        findByEmail(obj);
        User newObj = mapper.map(obj, User.class);
        return repository.save(newObj);
    }

    private void findByEmail(UserDTO obj) {
        Optional<User> user = repository.findByEmail(obj.getEmail());
            if(user.isPresent() && !user.get().getId().equals(obj.getId())) {
                throw new DataIntegratyViolationException("E-mail já cadastrado no sistema");
            }
        }
}
