package com.alvarolongueira.user.service.rest.api.handler;

import com.alvarolongueira.user.service.domain.exception.CreateUserException;
import com.alvarolongueira.user.service.domain.exception.UpdateUserException;
import com.alvarolongueira.user.service.domain.exception.UserNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CreateUserException.class)
    public ResponseEntity<String> handleCreateUserException(CreateUserException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UpdateUserException.class)
    public ResponseEntity<String> handleUpdateUserException(UpdateUserException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
