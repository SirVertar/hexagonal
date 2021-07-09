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
        return UserResponse.mapToUserRequest(facade.saveUser(userRequest.mapToUser()));
    }

    @PutMapping()
    UserResponse changeName(@RequestBody UserRequest userRequest) {
        return UserResponse.mapToUserRequest(facade.changeUserName(userRequest.getId(), userRequest.getName()));
    }

    @GetMapping("/{id}")
    UserResponse getUser(@PathVariable Long id) {
        return UserResponse.mapToUserRequest(facade.getUser(id));
    }

    @GetMapping()
    List<UserResponse> getUsers() {
        return facade.getUsers().stream()
                .map(UserResponse::mapToUserRequest)
                .collect(Collectors.toList());
    }
}
