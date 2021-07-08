package com.jakuszko.hexagonal.adapters.userdb;

import com.jakuszko.hexagonal.adapters.exceptions.UserNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRepositoryImpl {

    private final UserRepository repository;

    public UserRepositoryImpl(@Lazy UserRepository repository) {
        this.repository = repository;
    }

    public UserEntity save(UserEntity user) {
        return repository.save(user);
    }

    public UserEntity get(Long id) {
        return repository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public UserEntity update(UserEntity user) {
        return repository.save(user);
    }

    public List<UserEntity> getUsers() {
        return repository.findAll();
    }
}
