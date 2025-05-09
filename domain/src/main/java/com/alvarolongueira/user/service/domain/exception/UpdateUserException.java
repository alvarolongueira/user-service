package com.alvarolongueira.user.service.domain.exception;

public class UpdateUserException extends RuntimeException {

    private static final String MESSAGE = "Error updating user";

    public UpdateUserException() {
        super(MESSAGE);
    }
}
