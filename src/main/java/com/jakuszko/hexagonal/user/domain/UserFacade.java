package com.jakuszko.hexagonal.user.domain;

import com.jakuszko.hexagonal.user.domain.ports.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository repository;
    private static final Long RESPECT_POINTS = 100L;

    public User changeUserName(Long userId, String name) {
        User user = repository.getUser(userId);
        user.changeName(name);
        return repository.updateUser(user);
    }

    @Transactional
    public User addRespectPoints(Long userId) {
        User user = repository.getUser(userId);
        user.addRespectPoints(RESPECT_POINTS);
        return repository.updateUser(user);
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
