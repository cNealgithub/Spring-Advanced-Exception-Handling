package com.cNealgithub.exceptionhandlingPractise.controller;

import com.cNealgithub.exceptionhandlingPractise.dto.StudentRequestDto;
import com.cNealgithub.exceptionhandlingPractise.dto.StudentResponseDto;
import com.cNealgithub.exceptionhandlingPractise.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@Valid @RequestBody StudentRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createNewStudent(requestDto));
    }
    @GetMapping
    public ResponseEntity<Page<StudentResponseDto>> studentsList(@RequestParam(name = "page") int page,
                                                                 @RequestParam(name = "size") int size,
                                                                 @RequestParam(required = false, name = "sortBy", defaultValue = "studentId") String sortBy) {
        return ResponseEntity.ok(service.getAllStudents(page, size, sortBy));
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable("id") long studentId) {
        return ResponseEntity.ok(service.GetStudentById(studentId));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") long studentId) {
        service.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }
}
