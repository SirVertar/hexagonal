package com.jakuszko.hexagonal.adapters.api

import com.jakuszko.hexagonal.adapters.userdb.UserEntity
import com.jakuszko.hexagonal.adapters.userdb.UserRepositoryImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class UserFacadeSpecification extends Specification {

    @Autowired
    private UserFacade userFacade
    @Autowired
    private UserRepositoryImpl repository

    def "should change user name"() {
        given:
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .name("Marek")
                .surname("Jakuszko")
                .address("Terespol").build()
        repository.save(userEntity)

        when:
        UserResponse user = userFacade.changeName(createUserRequest())

        then:
        user.getName() == "Mateusz"
    }

    private static UserRequest createUserRequest() {
        UserRequest.builder()
                .id(1L)
                .name("Mateusz")
                .surname("Jakuszko")
                .address("Terespol").build()
    }
}
