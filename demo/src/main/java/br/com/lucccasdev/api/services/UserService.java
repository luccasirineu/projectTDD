package br.com.lucccasdev.api.services;


import br.com.lucccasdev.api.domain.User;
import br.com.lucccasdev.api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    public User findById(Integer id);
    public List<User> findAll();
    public User create(UserDTO obj);
    public User update(UserDTO obj);
    public void delete(Integer id);
}
