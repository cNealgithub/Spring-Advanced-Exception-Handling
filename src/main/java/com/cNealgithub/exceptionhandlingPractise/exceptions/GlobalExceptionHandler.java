package com.cNealgithub.exceptionhandlingPractise.exceptions;

import com.cNealgithub.exceptionhandlingPractise.dto.GlobalExceptionResponseDto;
import com.cNealgithub.exceptionhandlingPractise.dto.ValidationExceptionResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GlobalExceptionResponseDto> handleRuntimeException(
            RuntimeException ex, HttpServletRequest request) {

        GlobalExceptionResponseDto exceptionDto = new GlobalExceptionResponseDto();
        exceptionDto.setTimestamp(LocalDateTime.now());
        exceptionDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        exceptionDto.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        exceptionDto.setMessage(exceptionDto.getMessage());
        exceptionDto.setPath(request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionDto);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponseDto> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError ->
                        fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage()));

        ValidationExceptionResponseDto exceptionResponseDto = new ValidationExceptionResponseDto();
        exceptionResponseDto.setTimestamp(LocalDateTime.now());
        exceptionResponseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
        exceptionResponseDto.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        exceptionResponseDto.setMessage(ex.getMessage());
        exceptionResponseDto.setPath(request.getRequestURI());
        exceptionResponseDto.setFieldErrors(fieldErrors);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionResponseDto);
    }
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<GlobalExceptionResponseDto> handleRecordNotFoundException(
            RecordNotFoundException ex, HttpServletRequest request) {

        GlobalExceptionResponseDto exceptionDto = new GlobalExceptionResponseDto();
        exceptionDto.setTimestamp(LocalDateTime.now());
        exceptionDto.setStatusCode(HttpStatus.NOT_FOUND.value());
        exceptionDto.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        exceptionDto.setMessage(ex.getMessage());
        exceptionDto.setPath(request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionDto);
    }
    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<GlobalExceptionResponseDto> handleDuplicateRecordException(
            DuplicateRecordException ex, HttpServletRequest request) {

        GlobalExceptionResponseDto exceptionDto = new GlobalExceptionResponseDto();
        exceptionDto.setTimestamp(LocalDateTime.now());
        exceptionDto.setStatusCode(HttpStatus.CONFLICT.value());
        exceptionDto.setError(HttpStatus.CONFLICT.getReasonPhrase());
        exceptionDto.setMessage(ex.getMessage());
        exceptionDto.setPath(request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exceptionDto);
    }
}
