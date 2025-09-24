
package com.example.api_escola.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", message);
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(AlunoException.class)
    public ResponseEntity<Map<String, Object>> handleAlunoException(AlunoException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(ProfessorException.class)
    public ResponseEntity<Map<String, Object>> handleProfessorException(ProfessorException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(TurmaException.class)
    public ResponseEntity<Map<String, Object>> handleTurmaException(TurmaException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(InscricaoException.class)
    public ResponseEntity<Map<String, Object>> handleInscricaoException(InscricaoException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
