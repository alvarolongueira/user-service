package com.alvarolongueira.user.service.domain.exception;

public class CreateUserException extends RuntimeException {

    private static final String MESSAGE = "Error creating user";

    public CreateUserException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
