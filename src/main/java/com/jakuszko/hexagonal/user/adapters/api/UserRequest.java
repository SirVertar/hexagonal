package com.jakuszko.hexagonal.user.adapters.api;

import com.jakuszko.hexagonal.user.domain.User;
import lombok.*;

@Builder
@Data
public class UserRequest {

    private final Long id;
    private final String name;
    private final String surname;
    private final String address;

    User mapToUser() {
        return User.builder()
                .id(this.getId())
                .name(this.getName())
                .surname(this.getSurname())
                .address(this.getAddress()).build();
    }
}
