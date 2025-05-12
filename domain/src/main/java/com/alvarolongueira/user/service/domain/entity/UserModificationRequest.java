package com.alvarolongueira.user.service.domain.entity;

import lombok.Builder;

@Builder
public record UserModificationRequest(
        String id,
        String firstName,
        String lastName,
        String nickname,
        String password,
        String email,
        String country) {}
