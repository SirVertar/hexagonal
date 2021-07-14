package com.jakuszko.hexagonal.user.adapters.api;

import com.jakuszko.hexagonal.user.domain.User;
import lombok.*;

@Builder
@Data
@Getter
public class UserResponse {

    private final Long id;
    private final String name;
    private final String surname;
    private final String address;

    static UserResponse fromDomain(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .address(user.getAddress()).build();
    }
}
