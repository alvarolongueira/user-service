package com.alvarolongueira.user.service.domain.entity;

import lombok.Builder;

@Builder
public record UserSearchRequest(
        Integer page, Integer size, String firstName, String lastName, String country) {}
