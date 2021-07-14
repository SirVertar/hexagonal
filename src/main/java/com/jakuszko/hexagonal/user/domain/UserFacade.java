package com.jakuszko.hexagonal.user.domain;

import com.jakuszko.hexagonal.user.domain.ports.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository repository;

    public User changeUserName(Long userId, String name) {
        User user = repository.getUser(userId);
        user.changeName(name);
        return repository.saveUser(user);
    }

    public User saveUser(User user) {
        return repository.saveUser(user);
    }

    public List<User> getUsers() {
        return repository.getUsers();
    }

    public User getUser(Long id) {
        return repository.getUser(id);
    }

}
