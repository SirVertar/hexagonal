package com.jakuszko.hexagonal.adapters.api;

import com.jakuszko.hexagonal.adapters.userdb.UserMapper;
import com.jakuszko.hexagonal.domain.model.User;
import com.jakuszko.hexagonal.domain.ports.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UserFacade {

    private final UserService userService;
    private final UserMapper mapper;

    UserResponse changeName(UserRequest userRequest) {
        User user = userService.changeUserName(userRequest.getId(), userRequest.getName());
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .address(user.getAddress()).build();
    }

    UserResponse saveUser(UserRequest userRequest) {
        User user = userService.saveUser(mapper.mapToUser(userRequest));
        return mapper.mapToUserResponse(userService.saveUser(user));
    }

    List<UserResponse> getUsers() {
        return userService.getUsersList().stream()
                .map(mapper::mapToUserResponse)
                .collect(Collectors.toList());
    }
}
