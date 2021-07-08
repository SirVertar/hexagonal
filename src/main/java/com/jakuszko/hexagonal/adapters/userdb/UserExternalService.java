package com.jakuszko.hexagonal.adapters.userdb;

import com.jakuszko.hexagonal.domain.model.User;
import com.jakuszko.hexagonal.domain.ports.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
class UserExternalService implements UserService {

    private final UserRepositoryImpl repository;
    private final UserMapper mapper;

    @Override
    public User changeUserName(Long id, String name) {
        UserEntity userEntity = repository.get(id);
        userEntity.setName(name);
        return mapper.mapToUser(repository.update(userEntity));
    }

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = repository.save(mapper.mapToUserEntity(user));
        return mapper.mapToUser(userEntity);
    }

    @Override
    public List<User> getUsersList() {
        return repository.getUsers().stream()
                .map(mapper::mapToUser)
                .collect(Collectors.toList());
    }
}
