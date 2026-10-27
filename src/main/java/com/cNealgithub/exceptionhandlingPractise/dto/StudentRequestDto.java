package com.cNealgithub.exceptionhandlingPractise.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentRequestDto {

    @NotBlank(message = "First Name cannot be blank")
    private String firstName;
    @NotBlank(message = "Last Name cannot be blank")
    private String lastName;
    @Max(value = 300, message = "Roll no cannot exceed more than 300")
    private int rollNo;
    @Email(message = "Email must be valid")
    private String email;
    @NotBlank(message = "Course cannot be blank")
    private String course;
}
