package com.jakuszko.hexagonal.user

import com.jakuszko.hexagonal.user.adapters.userdb.JpaUserRepositoryFacade
import com.jakuszko.hexagonal.user.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class IntegrationTest extends Specification {

    @Autowired
    JpaUserRepositoryFacade userRepositoryFacade

    User createdUser
    WebTestClient webClient


    def setup() {
        webClient = WebTestClient.bindToServer()
                .baseUrl('http://localhost:8080')
                .build()
        createdUser = createUser()
    }

    User createUser() {
        return userRepositoryFacade.saveUser(User.builder()
        .id(null)
        .name('Mateusz')
        .surname('Jakuszko')
        .address('Kraszewskiego').build())

    }
}
