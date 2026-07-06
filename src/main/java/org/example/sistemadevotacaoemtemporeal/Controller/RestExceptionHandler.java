package org.example.sistemadevotacaoemtemporeal.Controller;

import org.example.sistemadevotacaoemtemporeal.Exception.UserNotFoundException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.example.sistemadevotacaoemtemporeal.Exception.EmailAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("message", exception.getMessage());
        error.put("status", "404");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExists(EmailAlreadyExistsException exeption) {
        Map<String, String> error = new HashMap<>();
        error.put("message", exeption.getMessage());
        error.put("status", "422");

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}

