package com.example.demo.exception;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO>
    handleNotFound(ResourceNotFoundException ex) {

        return new ResponseEntity<>(
                new ErrorResponseDTO(ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO>
    handleGeneric(Exception ex) {

        return new ResponseEntity<>(
                new ErrorResponseDTO(ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}