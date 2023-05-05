package com.example.university.service;


import com.example.university.entity.Student;
import com.example.university.payload.Result;
import com.example.university.payload.StudentPayload;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface StudentService {
    Result saveStudent(StudentPayload studentPayload);
    Result editStudent(UUID id, StudentPayload studentPayload);
    Result deleteStudent(UUID id);
    Result changeStudentIsView(UUID id);

    Page<Student> getAllStudentWithPageable(int page, int size);

    Result getStudyType();
    Result findStudentById(UUID studentId);
}
