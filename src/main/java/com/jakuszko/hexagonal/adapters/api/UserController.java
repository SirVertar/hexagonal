package com.jakuszko.hexagonal.adapters.api;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserFacade facade;

    public UserController(UserFacade facade) {
        this.facade = facade;
    }

    @PostMapping()
    UserResponse saveUser(@RequestBody UserRequest userRequest) {
        return facade.saveUser(userRequest);
    }

    @PutMapping()
    UserResponse changeName(@RequestBody UserRequest userRequest) {
        return facade.changeName(userRequest);
    }

    @GetMapping()
    List<UserResponse> getUsers() {
        return facade.getUsers();
    }
}
