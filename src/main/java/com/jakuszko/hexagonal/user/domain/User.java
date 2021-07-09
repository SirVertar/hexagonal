package com.jakuszko.hexagonal.user.domain;

import com.jakuszko.hexagonal.user.domain.exceptions.ShortUserNameException;
import com.jakuszko.hexagonal.user.domain.exceptions.UserNameContainNumbersException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class User {

    private final Long id;
    private String name;
    private final String surname;
    private final String address;

    void changeName(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name.length() < 5) {
            throw new ShortUserNameException();
        } else if (name.matches(".*\\d.*")) {
            throw new UserNameContainNumbersException();
        }
    }
}
