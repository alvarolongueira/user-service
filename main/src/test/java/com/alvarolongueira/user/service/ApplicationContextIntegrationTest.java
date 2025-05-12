package com.alvarolongueira.user.service;

import com.alvarolongueira.user.service.testcontainer.AbstractTestContainer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
class ApplicationContextIntegrationTest extends AbstractTestContainer {

    @Test
    void contextLoads() {}
}
