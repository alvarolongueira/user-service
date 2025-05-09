package com.alvarolongueira.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.alvarolongueira.user.service.rest.api.model.CreateUser201Response;
import com.alvarolongueira.user.service.rest.api.model.CreateUserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@EmbeddedKafka(
        partitions = 1,
        brokerProperties = {"listeners=PLAINTEXT://localhost:29092", "port=29092"})
class UserIntegrationTest {

    @LocalServerPort private int port;
    @Autowired private TestRestTemplate restTemplate;

    @Test()
    void createUserSuccessfully() {
        CreateUserRequest request =
                new CreateUserRequest(
                        "firstName", "lastName", "nickname", "password", "email", "country");
        ResponseEntity<CreateUser201Response> response =
                restTemplate.postForEntity(getUrl(), request, CreateUser201Response.class);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value());
        assertNotNull(response.getBody().getId());
    }

    private String getUrl() {
        return "http://localhost:" + port + "/user";
    }
}
