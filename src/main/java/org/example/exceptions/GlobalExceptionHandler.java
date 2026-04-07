package com.exemplo.api.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// Intercepta exceções lançadas em todos os controllers
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Desafio 2: Captura erros de validação (@Valid) e formata a resposta
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        // Pega a mensagem de erro do primeiro campo que falhou na validação
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        Map<String, String> response = new HashMap<>();
        response.put("erro", errorMessage);

        return ResponseEntity.badRequest().body(response); // 400
    }
}