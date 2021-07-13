package com.jakuszko.hexagonal.user.domain

import com.jakuszko.hexagonal.user.InMemoryUserRepository
import com.jakuszko.hexagonal.user.UnitTest
import com.jakuszko.hexagonal.user.domain.exceptions.ShortUserNameException
import com.jakuszko.hexagonal.user.domain.exceptions.UserNameContainNumbersException

class UserFacadeSpecification extends UnitTest {

    InMemoryUserRepository userRepository = new InMemoryUserRepository()
    UserFacade userFacade = new UserFacade(userRepository)

    def cleanup() {
        userRepository.cleanRepository()
    }

    def "should save user"() {
        when:
        User user = User.builder()
                .id(321L)
                .name('Marek')
                .surname('Witczak')
                .address('Poznań').build()
        User savedUser = userFacade.saveUser(user)

        then:
        savedUser.getId() == user.getId()
        savedUser.getName() == user.getName()
        savedUser.getSurname() == user.getSurname()
        savedUser.getAddress() == user.getAddress()
    }

    def "should get user"() {
        given:
        userRepository.withUser()
        User user = userRepository.getUsers()[0]

        when:
        User fetchedUser = userFacade.getUser(user.getId())

        then:
        fetchedUser.getId() == user.getId()
        fetchedUser.getName() == user.getName()
        fetchedUser.getSurname() == user.getSurname()
        fetchedUser.getAddress() == user.getAddress()
    }

    def "should change name of a user"() {
        given:
        userRepository.withUser()
        User user = userRepository.getUsers()[0]

        when:
        userFacade.changeUserName(user.getId(), "Katarzyna")

        then:
        userFacade.getUser(user.getId()).getName() == "Katarzyna"
    }

    def "shouldn't change name of user because of name consist of digits"() {
        given:
        userRepository.withUser()
        User user = userRepository.getUsers()[0]

        when:
        userFacade.changeUserName(user.getId(), name)

        then:
        thrown(UserNameContainNumbersException)

        where:
        name << ['Mateusz1', '35324', '32Mat432ds43', 'Mateu3usz']
    }

    def "shouldn't change name of user because of that name is toShort"() {
        given:
        userRepository.withUser()
        User user = userRepository.getUsers()[0]

        when:
        userFacade.changeUserName(user.getId(), name)

        then:
        thrown(ShortUserNameException)

        where:
        name << ['Mat', 'a', 'Mate']
    }
}
