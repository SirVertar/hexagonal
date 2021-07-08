package com.jakuszko.hexagonal.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class User {

    private final Long id;
    private final String name;
    private final String surname;
    private final String address;
}
