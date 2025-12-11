package com.example.catalog_service.controller;

import com.example.catalog_service.exception.ApiError;
import com.example.catalog_service.exception.BusinessRuleException;
import com.example.catalog_service.exception.DuplicateResourceException;
import com.example.catalog_service.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ControllerHandler extends ResponseEntityExceptionHandler {

    // --- 404 NOT FOUND ---
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundExceptions(ResourceNotFoundException ex) {
        ApiError apiError = new ApiError("Recurso no encontrado: " + ex.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    // --- 409 CONFLICT ---
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiError> handleConflictExceptions(DuplicateResourceException ex) {
        ApiError apiError = new ApiError("Conflicto: " + ex.getMessage(), HttpStatus.CONFLICT, LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    // --- 400 BAD REQUEST ---
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ApiError> handleBadRequestExceptions(BusinessRuleException ex) {
        ApiError apiError = new ApiError("Petición inválida: " + ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    // --- 500 INTERNAL SERVER ERROR ---
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleInternalServerExceptions(Exception ex) {
        ApiError apiError = new ApiError("Error interno del servidor: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        BindingResult bindingResult = ex.getBindingResult();
        List<ObjectError> errorList = bindingResult.getAllErrors();
        StringBuilder errorMessage = new StringBuilder("Error de validación: ");

        for (ObjectError objectError : errorList) {
            if (objectError instanceof FieldError fieldError) {
                errorMessage.append("[").append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage()).append("] ");
            } else {
                errorMessage.append("[").append(objectError.getDefaultMessage()).append("] ");
            }
        }

        ApiError apiError = new ApiError(errorMessage.toString().trim(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
