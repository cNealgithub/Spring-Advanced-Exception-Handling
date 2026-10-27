package com.cNealgithub.exceptionhandlingPractise.dto;

import lombok.Data;

@Data
public class StudentResponseDto {

    private long studentId;
    private String firstName;
    private String lastName;
    private int rollNo;
    private String email;
    private String course;
}
