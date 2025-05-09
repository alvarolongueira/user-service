package com.alvarolongueira.user.service.testcontainer;

import org.junit.ClassRule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@EmbeddedKafka(
        partitions = 1,
        brokerProperties = {"listeners=PLAINTEXT://localhost:29092", "port=29092"})
public abstract class AbstractTestContainer {

    @ClassRule
    public static PostgreSQLContainer postgres =
            new PostgreSQLContainer("postgres:13.1-alpine")
                    .withDatabaseName("application_database")
                    .withUsername("database-user")
                    .withPassword("database-pass");

    @BeforeAll
    public static void beforeAll() throws InterruptedException {
        postgres.start();
        // TODO remove this
        Thread.sleep(5000L);
    }

    @AfterAll
    public static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }
}
