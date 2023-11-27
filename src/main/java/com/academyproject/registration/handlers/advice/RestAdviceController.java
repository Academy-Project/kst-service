package com.academyproject.registration.handlers.advice;

import com.academyproject.registration.handlers.exceptions.NotFoundException;
import com.academyproject.registration.resources.ResourceHandler;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestAdviceController {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
        List<String> errorMessages = e.getConstraintViolations()
                .stream()
                .map(constraintViolation -> String.format("%s attribute %s", constraintViolation.getPropertyPath(), constraintViolation.getMessage()))
                .collect(Collectors.toList());

        return ResourceHandler.errorResponse(errorMessages, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> responseException(ResponseStatusException e) {
        return ResourceHandler.errorResponse(
                Collections.singletonList(e.getReason()),
                (HttpStatus) e.getStatusCode()
        );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFoundException(NotFoundException e) {
        return ResourceHandler.errorResponse(
                Collections.singletonList(e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
