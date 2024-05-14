package br.com.lucccasdev.api.services;


import br.com.lucccasdev.api.domain.User;

import java.util.List;

public interface UserService {

    public User findById(Integer id);
    public List<User> findAll();
}
