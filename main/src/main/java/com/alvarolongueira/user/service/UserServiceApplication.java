package com.alvarolongueira.user.service;

import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.infrastructure.database.adapter.UserDataOutputPortAdapter;
import com.alvarolongueira.user.service.infrastructure.database.repository.UserRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
