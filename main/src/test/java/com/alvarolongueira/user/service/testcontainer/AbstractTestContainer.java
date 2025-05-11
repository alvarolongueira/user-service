package com.alvarolongueira.user.service.testcontainer;

import io.restassured.RestAssured;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.kafka.KafkaContainer;

public abstract class AbstractTestContainer {

    public static final String USER_PATH = "/user";

    static PostgreSQLContainer postgres =
            new PostgreSQLContainer("postgres:13.1-alpine")
                    .withDatabaseName("application_database")
                    .withUsername("database-user")
                    .withPassword("database-pass");

    static KafkaContainer kafka = new KafkaContainer("apache/kafka-native:3.8.0");

    @LocalServerPort private int port;

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }

    @BeforeAll
    public static void beforeAll() {
        postgres.start();
        kafka.start();
        // TODO remove this
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    public static void afterAll() {
        postgres.stop();
        kafka.stop();
    }

    @BeforeEach
    public void beforeEach() {
        postgres.waitingFor(Wait.forListeningPort());
        kafka.waitingFor(Wait.forListeningPort());
        RestAssured.baseURI = "http://localhost:" + port;
    }

    protected String getUserWithIdPath(String userId) {
        return USER_PATH + "/" + userId;
    }
}
