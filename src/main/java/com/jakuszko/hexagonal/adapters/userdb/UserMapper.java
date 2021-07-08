package com.jakuszko.hexagonal.adapters.userdb;

import com.jakuszko.hexagonal.adapters.api.UserRequest;
import com.jakuszko.hexagonal.adapters.api.UserResponse;
import com.jakuszko.hexagonal.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToUser(UserEntity user) {
        return User.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .address(user.getAddress()).build();
    }

    public UserEntity mapToUserEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .address(user.getAddress()).build();
    }

    public User mapToUser(UserRequest userRequest) {
        return User.builder()
                .id(userRequest.getId())
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .address(userRequest.getAddress()).build();
    }

    public UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .address(user.getAddress()).build();
    }

}
