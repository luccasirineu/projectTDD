package br.com.lucccasdev.api.services;


import br.com.lucccasdev.api.domain.User;

public interface UserService {

    public User findById(Integer id);
}
