package com.alvarolongueira.user.service.domain.service;

import com.alvarolongueira.user.service.domain.entity.User;

import org.springframework.lang.Nullable;

public interface CreateUserUseCase extends UseCase<CreateUserUseCase.RequestUseCase, User> {

    User process(RequestUseCase request);

    record RequestUseCase(
            @Nullable String firstName,
            @Nullable String lastName,
            String nickname,
            @Nullable String password,
            String email,
            @Nullable String country) {}
}
