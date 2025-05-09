package com.alvarolongueira.user.service.domain.exception;

public class NotifyDeleteException extends RuntimeException {

    private static final String MESSAGE = "Error notifying user: %s";

    public NotifyDeleteException(String userId, Throwable cause) {
        super(String.format(MESSAGE, userId), cause);
    }
}
