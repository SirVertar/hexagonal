package com.jakuszko.hexagonal.adapters.userdb;

import lombok.*;
import org.springframework.stereotype.Component;

@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class UserDto {

    private Long id;
    private String name;
    private String surname;
    private String address;
}
