package com.jakuszko.hexagonal.user.domain;

import com.jakuszko.hexagonal.user.domain.exceptions.ShortUserNameException;
import com.jakuszko.hexagonal.user.domain.exceptions.UserNameContainNumbersException;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class User {

    private final Long id;
    private String name;
    private final String surname;
    private final String address;
    private Long respectPoints;

    void changeName(String name) {
        validate(name);
        this.name = name;
    }

    void addRespectPoints(Long respectPoints) {
        this.respectPoints += respectPoints;
    }

    private void validate(String name) {
        if (name.length() < 5) {
            throw new ShortUserNameException();
        } else if (name.matches(".*\\d.*")) {
            throw new UserNameContainNumbersException();
        }
    }
}
