package com.cNealgithub.exceptionhandlingPractise.service;

import com.cNealgithub.exceptionhandlingPractise.dto.StudentRequestDto;
import com.cNealgithub.exceptionhandlingPractise.dto.StudentResponseDto;
import com.cNealgithub.exceptionhandlingPractise.entity.Student;
import com.cNealgithub.exceptionhandlingPractise.exceptions.DuplicateRecordException;
import com.cNealgithub.exceptionhandlingPractise.exceptions.RecordNotFoundException;
import com.cNealgithub.exceptionhandlingPractise.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepo studentRepo;
    private final ModelMapper modelMapper;

    public StudentResponseDto createNewStudent(StudentRequestDto requestDto) {
        boolean alreadyExists= studentRepo.existsByEmail(requestDto.getEmail());
        if(alreadyExists){
            throw new DuplicateRecordException("User Already Exists by Email : " +
                    requestDto.getEmail());
        }
        Student newStudent = Student.builder()
                .firstName(requestDto.getFirstName())
                .lastName(requestDto.getLastName())
                .rollNo(requestDto.getRollNo())
                .email(requestDto.getEmail())
                .course(requestDto.getCourse())
                .build();
        Student savedStudent = studentRepo.save(newStudent);
        return modelMapper.map(savedStudent, StudentResponseDto.class);
    }

    public Page<StudentResponseDto> getAllStudents(int page, int size, String sortBY) {
        Pageable pageable= PageRequest.of(page, size, Sort.by(sortBY).ascending());
        Page<Student> studentPage = studentRepo.findAll(pageable);
        return studentPage.map(student -> modelMapper.map(student, StudentResponseDto.class));
    }

    public StudentResponseDto GetStudentById(long studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RecordNotFoundException("Student with id: " + studentId + " not found"));
        return modelMapper.map(student, StudentResponseDto.class);
    }

    public void deleteStudent(long studentId) {
        studentRepo.deleteById(studentId);
    }
}
