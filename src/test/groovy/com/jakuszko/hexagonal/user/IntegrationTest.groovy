package com.jakuszko.hexagonal.user

import com.fasterxml.jackson.databind.ObjectMapper
import com.jakuszko.hexagonal.user.adapters.userdb.JpaUserRepositoryFacade
import com.jakuszko.hexagonal.user.domain.User
import groovy.transform.NamedParam
import groovy.transform.NamedVariant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import reactor.core.publisher.Mono
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class IntegrationTest extends Specification {

    @Autowired
    JpaUserRepositoryFacade userRepositoryFacade
    @Autowired
    private ObjectMapper objectMapper

    WebTestClient webClient

    def setup() {
        webClient = WebTestClient.bindToServer()
                .baseUrl('http://localhost:8080')
                .build()
    }

    def cleanup() {
        userRepositoryFacade.deleteAllUsers()
    }

    User withUser() {
        return userRepositoryFacade.saveUser(User.builder()
                .id(null)
                .name('Mateusz')
                .surname('Jakuszko')
                .address('Kraszewskiego').build())
    }

    @NamedVariant
    WebTestClient.ResponseSpec postRequest(
            @NamedParam(required = true) String url,
            @NamedParam Map request = [:],
            @NamedParam MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>()) {

        return webClient.post()
                .uri(uriBuiler -> uriBuiler
                        .path(url)
                        .queryParams(queryParams)
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(objectMapper.writeValueAsString(request)), String)
                .exchange()
    }

    @NamedVariant
    WebTestClient.ResponseSpec putRequest(
            @NamedParam(required = true) String url,
            @NamedParam Map request = [:],
            @NamedParam MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>()) {

        return webClient.put()
                .uri(uriBuiler -> uriBuiler
                        .path(url)
                        .queryParams(queryParams)
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(objectMapper.writeValueAsString(request)), String)
                .exchange()
    }

    @NamedVariant
    WebTestClient.ResponseSpec getRequest(
            @NamedParam(required = true) String url,
            @NamedParam MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>()) {

        return webClient.get()
                .uri(uriBuiler -> uriBuiler
                        .path(url)
                        .queryParams(queryParams)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
    }
}
