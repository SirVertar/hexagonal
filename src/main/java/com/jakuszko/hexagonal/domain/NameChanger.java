package com.jakuszko.hexagonal.domain;

import com.jakuszko.hexagonal.domain.model.User;
import com.jakuszko.hexagonal.domain.ports.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NameChanger {

    private final UserService userService;

    public User changeUserName(Long id, String name) {
        return userService.changeUserName(id, name);
    }
}
