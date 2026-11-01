package com.cNealgithub.exceptionhandlingPractise.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GlobalExceptionResponseDto {

    private LocalDateTime timestamp;
    private int statusCode;
    private String error;
    private String message;
    private String path;
}
