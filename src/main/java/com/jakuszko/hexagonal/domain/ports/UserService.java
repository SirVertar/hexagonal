package com.jakuszko.hexagonal.domain.ports;

import com.jakuszko.hexagonal.domain.model.User;

import java.util.List;

public interface UserService {

    User changeUserName(Long id, String name);
    User saveUser(User user);
    List<User> getUsersList();
}
