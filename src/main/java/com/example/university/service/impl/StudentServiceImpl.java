package com.example.university.service.impl;

import com.example.university.entity.Student;
import com.example.university.exceptions.ResourceNotFound;
import com.example.university.payload.Result;
import com.example.university.payload.StudentPayload;
import com.example.university.repository.FacultyRepository;
import com.example.university.repository.StudentRepository;
import com.example.university.repository.StudyTypeRepository;
import com.example.university.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final MyFileService myFileService;
    private final FacultyRepository facultyRepository;
    private final StudyTypeRepository studyTypeRepository;


    @Override
    public Result saveStudent(StudentPayload studentPayload) {
        try {
            Student student = new Student();
            student.setFirstName(studentPayload.getFirstName());
            student.setLastName(studentPayload.getLastName());
            student.setPhoneNumber(studentPayload.getPhoneNumber());
            student.setEmail(studentPayload.getEmail());
            student.setStudyType(studyTypeRepository.findById(studentPayload.getStudyTypeId()).orElseThrow(
                    () -> new ResourceNotFound("study type", "id", studentPayload.getStudyTypeId())
            ));
            student.setFaculty(facultyRepository.findById(studentPayload.getFacultyId()).orElseThrow(
                    () -> new ResourceNotFound("faculty", "id", studentPayload.getFacultyId())
            ));
            student.setPassportFile(myFileService.findByHashId(studentPayload.getPassportHashId()));
            student.setAttestatFile(myFileService.findByHashId(studentPayload.getAttestatHashId()));
            student.setView(false);
            studentRepository.save(student);
            return Result.success(student);
        } catch (Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public Result editStudent(UUID id, StudentPayload studentPayload) {
        try {
            Student student = studentRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFound("student", "id", id)
            );
            if (studentPayload.getFirstName() != null)
                student.setFirstName(studentPayload.getFirstName());
            if (studentPayload.getLastName() != null)
                student.setLastName(studentPayload.getLastName());
            if (studentPayload.getPhoneNumber() != null)
                student.setPhoneNumber(studentPayload.getPhoneNumber());
            if (studentPayload.getEmail() != null)
                student.setEmail(studentPayload.getEmail());
            if (studentPayload.getStudyTypeId() != null)
                student.setStudyType(studyTypeRepository.findById(studentPayload.getStudyTypeId()).orElseThrow(
                        () -> new ResourceNotFound("study type", "id", studentPayload.getStudyTypeId())
                ));
            if (studentPayload.getFacultyId() != null)
                student.setFaculty(facultyRepository.findById(studentPayload.getFacultyId()).orElseThrow(
                        () -> new ResourceNotFound("faculty", "id", studentPayload.getFacultyId())
                ));
            if (studentPayload.getPassportHashId() != null)
                student.setPassportFile(myFileService.findByHashId(studentPayload.getPassportHashId()));
            if (studentPayload.getAttestatHashId() != null)
                student.setAttestatFile(myFileService.findByHashId(studentPayload.getAttestatHashId()));
            studentRepository.save(student);
            return Result.success(student);
        } catch (Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public Result deleteStudent(UUID id) {
        try {
            Student student = studentRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFound("student", "id", id)
            );
            studentRepository.deleteById(id);
            new File(String.format("%s/%s.%s", student.getAttestatFile().getUploadPath(), student.getAttestatFile().getHashId(), student.getAttestatFile().getExtension())).delete();
            new File(String.format("%s/%s.%s", student.getPassportFile().getUploadPath(), student.getPassportFile().getHashId(), student.getPassportFile().getExtension())).delete();
            return Result.message("succesful deleted", true);
        } catch (Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public Result changeStudentIsView(UUID id) {
        try {
            Student student = studentRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFound("student", "id", id)
            );
            student.setView(!student.isView());
            return Result.success(student);
        } catch (Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public Page<Student> getAllStudentWithPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return studentRepository.findAll(pageable);
    }

    @Override
    public Result getStudyType() {
        return Result.success(studyTypeRepository.findAll(Sort.by("id")));
    }

    @Override
    public Result findStudentById(UUID studentId) {
        try {
            return Result.success(studentRepository.findById(studentId).orElseThrow(
                    () -> new ResourceNotFound("student", "id", studentId)
            ));
        }catch (Exception e){
            return Result.exception(e);
        }
    }
}
