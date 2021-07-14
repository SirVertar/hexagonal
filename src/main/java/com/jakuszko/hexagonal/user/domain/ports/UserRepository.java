package com.jakuszko.hexagonal.user.domain.ports;


import com.jakuszko.hexagonal.user.domain.User;

import java.util.List;

public interface UserRepository {

    User saveUser(User user);

    User updateUser(User user);

    User getUser(Long id);

    List<User> getUsers();
}
