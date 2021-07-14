package com.jakuszko.hexagonal.user.adapters.userdb;

import com.jakuszko.hexagonal.user.domain.User;
import com.jakuszko.hexagonal.user.domain.ports.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JpaUserRepositoryFacade implements UserRepository {

    private final JpaUserRepository repository;

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = UserEntity.fromUser(user);
        if (userEntity.getRespectPoints() == null) {
            userEntity.setRespectPoints(0L);
        }
        return UserEntity.mapToUser(repository.save(userEntity));

    }

    @Override
    public User updateUser(User user) {
        UserEntity userEntity = UserEntity.fromUser(user);
        return UserEntity.mapToUser(repository.save(userEntity));
    }


    @Override
    public User getUser(Long id) {
        return UserEntity.mapToUser(repository.getById(id));
    }

    @Override
    public List<User> getUsers() {
        return repository.findAll().stream()
                .map(UserEntity::mapToUser)
                .collect(Collectors.toList());
    }
}
