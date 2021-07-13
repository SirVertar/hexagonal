package com.jakuszko.hexagonal.user.adapters.api

import com.jakuszko.hexagonal.user.IntegrationTest
import com.jakuszko.hexagonal.user.domain.User
import org.springframework.test.web.reactive.server.WebTestClient

class UserControllerSpecification extends IntegrationTest {

    def "should post user"() {
        when:
        WebTestClient.ResponseSpec responseSpec = postRequest(
                url: '/users',
                request: [name: "Marcin", surname: "Wloszynski", address: "Poznan"]
        )

        then:
        responseSpec.expectStatus().isOk()
        responseSpec.expectBody()
                .jsonPath('$.id').isNotEmpty()
                .jsonPath('$.name').isEqualTo("Marcin")
                .jsonPath('$.surname').isEqualTo("Wloszynski")
                .jsonPath('$.address').isEqualTo("Poznan")
    }

    def "should get user"() {
        given:
        User user = withUser()
        String userId = String.valueOf(user.getId())

        when:
        WebTestClient.ResponseSpec responseSpec = getRequest(url: String.format("/users/%s", userId))

        then:
        responseSpec.expectStatus().isOk()
        responseSpec.expectBody()
                .jsonPath('$.id').isEqualTo(userId)
                .jsonPath('$.name').isEqualTo(user.getName())
                .jsonPath('$.surname').isEqualTo(user.getSurname())
                .jsonPath('$.address').isEqualTo(user.getAddress())
    }

    def "should change name"() {
        given:
        User user = withUser()
        String userId = String.valueOf(user.getId())

        when:
        String changedName = "Bartosz"
        putRequest(
                url: String.format("/users/%s/name/%s", userId, changedName)
        )

        then:
        getRequest(
                url: String.format("/users/%s", userId)).expectBody()
                .jsonPath('$.id').isEqualTo(userId)
                .jsonPath('$.name').isEqualTo(changedName)
                .jsonPath('$.surname').isEqualTo(user.getSurname())
                .jsonPath('$.address').isEqualTo(user.getAddress())
    }
}
