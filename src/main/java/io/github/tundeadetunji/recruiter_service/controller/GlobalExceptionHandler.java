package io.github.tundeadetunji.recruiter_service.controller;

import io.github.tundeadetunji.recruiter_service.dto.ErrorDto;
import io.github.tundeadetunji.recruiter_service.exception.RecordNotFoundException;
import io.github.tundeadetunji.recruiter_service.exception.UnauthorizedAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ErrorDto<String>> handleRecordNotFoundException(RecordNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDto.create(exception.getMessage(), null));
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ErrorDto<String>> handleUnauthorizedAccessException(UnauthorizedAccessException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorDto.create(exception.getMessage(), null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

}
