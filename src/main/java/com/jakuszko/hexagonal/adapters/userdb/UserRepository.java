package com.jakuszko.hexagonal.adapters.userdb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
interface UserRepository extends JpaRepository<UserEntity, Long> {
}
