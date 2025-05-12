package com.alvarolongueira.user.service.domain.exception;

public class NotifyUpdateException extends RuntimeException {

    private static final String MESSAGE = "Error notifying user: %s";

    public NotifyUpdateException(String userId, Throwable cause) {
        super(String.format(MESSAGE, userId), cause);
    }
}
