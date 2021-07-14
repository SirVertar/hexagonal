package com.jakuszko.hexagonal.user.adapters.api;

import com.jakuszko.hexagonal.user.adapters.userdb.JpaUserRepositoryFacade;
import com.jakuszko.hexagonal.user.domain.UserFacade;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserFacade facade;

    public UserController(JpaUserRepositoryFacade repositoryFacade) {
        this.facade = new UserFacade(repositoryFacade);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    UserResponse saveUser(@RequestBody UserRequest userRequest) {
        return UserResponse.fromDomain(facade.saveUser(userRequest.mapToUser()));
    }

    @PutMapping("/{id}/name/{name}")
    UserResponse changeName(@PathVariable Long id, @PathVariable String name) {
        return UserResponse.fromDomain(facade.changeUserName(id, name));
    }

    @GetMapping("/{id}")
    UserResponse getUser(@PathVariable Long id) {
        return UserResponse.fromDomain(facade.getUser(id));
    }

    @GetMapping()
    List<UserResponse> getUsers() {
        return facade.getUsers().stream()
                .map(UserResponse::fromDomain)
                .collect(Collectors.toList());
    }
}
