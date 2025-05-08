package com.alvarolongueira.user.service;

import com.alvarolongueira.user.service.domain.entity.User;
import com.alvarolongueira.user.service.infrastructure.database.adapter.UserDataOutputPortAdapter;
import com.alvarolongueira.user.service.infrastructure.database.repository.UserRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        //        SpringApplication.run(UserServiceApplication.class, args);

        try {
            var context = SpringApplication.run(UserServiceApplication.class, args);
            var adapter = context.getBean(UserDataOutputPortAdapter.class);
            var repo = context.getBean(UserRepository.class);
            var user = User.builder().nickname("nick").email("mail").build();
            adapter.createUser(user);
            repo.findAll().forEach(System.out::println);
            var oo = repo.findById("5af53dec-ecb1-45db-8984-036406954e8b");
            System.out.println(oo);
            context.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
