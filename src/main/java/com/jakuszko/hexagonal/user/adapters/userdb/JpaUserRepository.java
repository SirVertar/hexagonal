package com.jakuszko.hexagonal.user.adapters.userdb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
}
