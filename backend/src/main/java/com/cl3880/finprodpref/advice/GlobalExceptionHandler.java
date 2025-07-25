package com.cl3880.finprodpref.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exceptions related to database integrity constraints such as unique key violations
     *
     * @param ex The exception thrown by the data layer.
     * @return A ResponseEntity with a 409 Conflict status and an error message.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String message = "Specified record already exists. Please check input.";
        if (ex.getCause() != null && ex.getCause().getMessage().contains("like_list_user_id_product_no_account_key")) {
            message = "Product is already in liked list for the selected account.";
        }
        return new ResponseEntity<>(Map.of("error", message), HttpStatus.CONFLICT);
    }
} 