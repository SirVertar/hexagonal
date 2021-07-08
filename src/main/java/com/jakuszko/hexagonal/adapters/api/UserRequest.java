package com.jakuszko.hexagonal.adapters.api;

import lombok.*;
import org.springframework.stereotype.Component;

@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRequest {

    private Long id;
    private String name;
    private String surname;
    private String address;
}
