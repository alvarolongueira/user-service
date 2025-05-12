package com.alvarolongueira.user.service.domain.exception;

public class NotifyCreateException extends RuntimeException {

    private static final String MESSAGE = "Error notifying user: %s";

    public NotifyCreateException(String userId, Throwable cause) {
        super(String.format(MESSAGE, userId), cause);
    }
}
