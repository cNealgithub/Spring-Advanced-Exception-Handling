package com.cNealgithub.exceptionhandlingPractise.repository;

import com.cNealgithub.exceptionhandlingPractise.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
}
