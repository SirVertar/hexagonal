package com.jakuszko.hexagonal.user

import com.jakuszko.hexagonal.user.domain.User
import com.jakuszko.hexagonal.user.domain.ports.UserRepository

class InMemoryUserRepository implements UserRepository {

    def map = [:]

    InMemoryUserRepository() {

    }

    @Override
    User saveUser(User user) {
        map.put(user.getId(), user)
        return map.get(user.getId()) as User
    }

    @Override
    User updateUser(User user) {
        map.put(user.getId(), user)
        return map.get(user.getId()) as User
    }

    @Override
    User getUser(Long id) {
        return map.get(id) as User
    }

    @Override
    List<User> getUsers() {
        return map.values() as List<User>
    }

    def withUser() {
        User user = User.builder()
                .id(1L)
                .name('Mateusz')
                .surname('Jakuszko')
                .address('Kraszewskiego').build()
        map.put(user.getId(), user)
    }

    def cleanRepository() {
        map.remove(1L)
    }
}
