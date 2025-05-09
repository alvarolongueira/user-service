package com.alvarolongueira.user.service.domain.exception;

public class UpdateUserException extends RuntimeException {

    private static final String MESSAGE = "Error updating user: %s";

    public UpdateUserException(String userId, Throwable cause) {
        super(String.format(MESSAGE, userId), cause);
    }
}
