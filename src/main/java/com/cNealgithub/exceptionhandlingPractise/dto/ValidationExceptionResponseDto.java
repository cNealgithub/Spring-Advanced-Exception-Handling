package com.cNealgithub.exceptionhandlingPractise.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ValidationExceptionResponseDto {

    private LocalDateTime timestamp;
    private int statusCode;
    private String error;
    private String message;
    private String path;
    private Map<String, String> fieldErrors;
}
