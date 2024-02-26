package com.demo.neo4j.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controller Advice Exception
 *
 * @author anh.nguyen
 * @created 26/02/2024
 */

@RestControllerAdvice
public class ControllerAdviceException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity throwException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
    }
}
