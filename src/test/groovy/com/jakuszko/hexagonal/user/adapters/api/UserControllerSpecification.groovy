package com.jakuszko.hexagonal.user.adapters.api


import com.jakuszko.hexagonal.user.IntegrationTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono

class UserControllerSpecification extends IntegrationTest {

    def "should post user"() {
        when:
        String userRequest = '{"name":"Marcin", "surname":"Wloszynski", "address":"Poznan"}'
        WebTestClient.ResponseSpec responseSpec = webClient.post().uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(userRequest), String)
                .exchange()

        then:
        responseSpec.expectStatus().isOk()
        responseSpec.expectBody().json('{"name":"Marcin", "surname":"Wloszynski", "address":"Poznan"}')

    }

    def "should get user"() {
        given:
        String userId = String.valueOf(createdUser.getId())

        when:
        WebTestClient.ResponseSpec responseSpec = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/users/{id}").build(userId))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()

        then:
        def expectedJson = '{\"id\":' + userId + ',' + '"name":"Mateusz", "surname":"Jakuszko", "address":"Kraszewskiego"}'
        responseSpec.expectStatus().isOk()
        responseSpec.expectBody().json(expectedJson)
    }

    def "should change name"() {
        given:
        String userId = String.valueOf(createdUser.getId())

        when:
        String userRequest = '{\"id\":' + userId + ',' + '"name":"Marcin", "surname":"Jakuszko", "address":"Kraszewskiego"}'
        webClient.put().uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(userRequest), String)
                .exchange()

        then:
        webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/users/{id}").build(userId))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBody().json(userRequest)
    }
}
