package br.com.lucccasdev.api.config;

import br.com.lucccasdev.api.domain.User;
import br.com.lucccasdev.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository repository;

    @Override
    public void run(String... args) throws Exception {

            User u1 = new User(null, "Luccas", "Luccasgirineu@gmail.com", "123");
            User u2 = new User(null, "Roberto", "robertoaalves@gmail.com", "123");

            repository.saveAll(List.of(u1,u2));


    }
}
