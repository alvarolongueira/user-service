package com.alvarolongueira.user.service.domain.exception;

public class UserNotFoundException extends RuntimeException {

    private static final String MESSAGE = "User not found: %s";

    public UserNotFoundException(String userId) {
        super(String.format(MESSAGE, userId));
    }
}
