package com.example.university.service.impl;

import com.example.university.entity.Faculty;
import com.example.university.exceptions.ResourceNotFound;
import com.example.university.payload.FacultyPayload;
import com.example.university.payload.Result;
import com.example.university.repository.FacultyRepository;
import com.example.university.repository.MyFileRepository;
import com.example.university.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;
    private final MyFileRepository myFileRepository;

    @Override
    public Result saveFaculty(FacultyPayload facultyPayload) {
        try {
            Faculty faculty = new Faculty();
            if (facultyPayload.getNameUz() != null)
                faculty.setNameUz(facultyPayload.getNameUz());
            if (facultyPayload.getNameRu() != null)
                faculty.setNameRu(facultyPayload.getNameRu());
            if (facultyPayload.getNameEn() != null)
                faculty.setNameEn(facultyPayload.getNameEn());
            if (facultyPayload.getHashId() != null)
                faculty.setIcon(myFileRepository.findByHashId(facultyPayload.getHashId()));

            facultyRepository.save(faculty);
            return Result.success(faculty);
        } catch (Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public Result editFaculty(UUID facultyId, FacultyPayload facultyPayload) {
        try {
            Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(
                    () -> new ResourceNotFound("faculty", "id", facultyId)
            );
            faculty.setNameUz(facultyPayload.getNameUz());
            faculty.setNameRu(facultyPayload.getNameRu());
            faculty.setNameEn(facultyPayload.getNameEn());
            faculty.setIcon(myFileRepository.findByHashId(facultyPayload.getHashId()));

            facultyRepository.save(faculty);
            return Result.success(faculty);
        } catch (Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public Result deleteFaculty(UUID facultyId) {
        try {
            Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(
                    () -> new ResourceNotFound("faculty", "id", facultyId)
            );
            facultyRepository.deleteById(facultyId);
            new File(String.format("%s/%s.%s", faculty.getIcon().getUploadPath(), faculty.getIcon().getHashId(), faculty.getIcon().getExtension())).delete();

            return Result.message("successful deleted", true);
        } catch (Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public Result getAll() {
        return Result.success(facultyRepository.findAll(Sort.by("createdAt")));
    }

    @Override
    public Result findNewsBoyId(UUID facultyId) {
        try {
            return Result.success(facultyRepository.findById(facultyId).orElseThrow(
                    () -> new ResourceNotFound("faculty", "id", facultyId)
            ));
        } catch (Exception e) {
            return Result.exception(e);
        }
    }
}
