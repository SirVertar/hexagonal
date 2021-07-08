package com.jakuszko.hexagonal.domain

import com.jakuszko.hexagonal.domain.model.User
import com.jakuszko.hexagonal.domain.ports.UserService
import spock.lang.Specification

class NameChangerSpecification extends Specification {

    UserService userService = Mock()
    NameChanger nameChanger = new NameChanger(userService)

    def "should change name of User"() {
        given:
        User user = User.builder()
                .id(1L)
                .name("Mateusz")
                .surname("Jakuszko")
                .address("Terespol").build()
        userService.changeUserName(1L, "Mateusz") >> user
        when:
        User updatedUser = nameChanger.changeUserName(1L, "Mateusz")

        then:
        updatedUser.getName() == "Mateusz"
        updatedUser.getId() == 1L
    }
}
