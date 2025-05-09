package com.alvarolongueira.user.service;

import com.alvarolongueira.user.service.testcontainer.PostgreTestContainer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
class ApplicationContextTests extends PostgreTestContainer {

    @Test
    void contextLoads() {}
}
