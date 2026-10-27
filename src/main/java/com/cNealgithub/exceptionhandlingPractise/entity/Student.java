package com.cNealgithub.exceptionhandlingPractise.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_record")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentId;
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true)
    private int rollNo;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String course;
}
