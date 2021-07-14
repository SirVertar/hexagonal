package com.jakuszko.hexagonal.user.adapters.userdb;

import com.jakuszko.hexagonal.user.domain.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private String address;
    private Long respectPoints;
    @Version
    private Long version;

    static User mapToUser(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .address(userEntity.getAddress())
                .respectPoints(userEntity.getRespectPoints()).build();
    }

    static UserEntity fromUser(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .address(user.getAddress())
                .respectPoints(user.getRespectPoints()).build();
    }
}
