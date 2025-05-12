package com.alvarolongueira.user.service.domain.entity;

import lombok.Builder;

import java.util.Optional;

@Builder
public record UserCreationRequest(
        String firstName,
        String lastName,
        String nickname,
        String password,
        String email,
        String country) {}
