package com.alvarolongueira.user.service.rest.api.adapters.input.response;

import java.util.UUID;

public record CityResponse(UUID id, String name, String state) {}
