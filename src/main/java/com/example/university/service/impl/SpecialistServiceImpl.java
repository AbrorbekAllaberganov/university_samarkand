package com.example.university.service.impl;

import com.example.university.entity.Department;
import com.example.university.entity.Specialist;
import com.example.university.exceptions.ResourceNotFound;
import com.example.university.payload.Result;
import com.example.university.payload.SpecialistPayload;
import com.example.university.repository.SpecialistRepository;
import com.example.university.repository.StudyTypeRepository;
import com.example.university.service.SpecialistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecialistServiceImpl implements SpecialistService {
    private final SpecialistRepository specialistRepository;
    private final MyFileService myFileService;
    private final StudyTypeRepository studyTypeRepository;


    @Override
    public Result saveSpecialist(SpecialistPayload specialistPayload) {
        try {
            Specialist specialist = new Specialist();
            specialist.setNameUz(specialistPayload.getNameUz());
            specialist.setNameRu(specialistPayload.getNameRu());
            specialist.setNameEn(specialistPayload.getNameEn());
            specialist.setBodyUz(specialistPayload.getBodyUz());
            specialist.setBodyRu(specialistPayload.getBodyRu());
            specialist.setBodyEn(specialistPayload.getBodyEn());
            specialist.setKvalifikatsiyaUz(specialistPayload.getKvalifikatsiyaUz());
            specialist.setKvalifikatsiyaRu(specialistPayload.getKvalifikatsiyaRu());
            specialist.setKvalifikatsiyaEn(specialistPayload.getKvalifikatsiyaEn());
            specialist.setImage(myFileService.findByHashId(specialistPayload.getImageHashId()));
//            List<Department> departments= specialistPayload.getDepartments().stream()
//                    .map(Department::new).toList();
            specialist.setDepartments(specialistPayload.getDepartments().stream()
                    .map(Department::new).toList());
            specialist.setStudyDuration(specialistPayload.getStudyDuration());
            specialist.setStudyType(studyTypeRepository.findById(specialistPayload.getStudyTypeId()).orElseThrow(
                    () -> new ResourceNotFound("study type", "id", specialistPayload.getStudyTypeId())
            ));

            specialistRepository.save(specialist);
            return Result.success(specialist);
        } catch (Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public Result editSpecialist(UUID id, SpecialistPayload specialistPayload) {
        try {
            Specialist specialist = specialistRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFound("specialist", "id", id)
            );
            if (specialistPayload.getNameUz() != null)
                specialist.setNameUz(specialistPayload.getNameUz());
            if (specialistPayload.getNameRu() != null)
                specialist.setNameRu(specialistPayload.getNameRu());
            if (specialistPayload.getNameEn() != null)
                specialist.setNameEn(specialistPayload.getNameEn());
            if (specialistPayload.getBodyUz() != null)
                specialist.setBodyUz(specialistPayload.getBodyUz());
            if (specialistPayload.getBodyRu() != null)
                specialist.setBodyRu(specialistPayload.getBodyRu());
            if (specialistPayload.getBodyEn() != null)
                specialist.setBodyEn(specialistPayload.getBodyEn());
            if (specialistPayload.getKvalifikatsiyaUz() != null)
                specialist.setKvalifikatsiyaUz(specialistPayload.getKvalifikatsiyaUz());
            if (specialistPayload.getKvalifikatsiyaRu() != null)
                specialist.setKvalifikatsiyaRu(specialistPayload.getKvalifikatsiyaRu());
            if (specialistPayload.getKvalifikatsiyaEn() != null)
                specialist.setKvalifikatsiyaEn(specialistPayload.getKvalifikatsiyaEn());
            if (specialistPayload.getImageHashId() != null)
                specialist.setImage(myFileService.findByHashId(specialistPayload.getImageHashId()));
            if (specialistPayload.getDepartments() != null)
                specialist.setDepartments(specialistPayload.getDepartments().stream()
                        .map(Department::new).toList());
            if (specialistPayload.getStudyDuration() != null)
                specialist.setStudyDuration(specialistPayload.getStudyDuration());
            if (specialistPayload.getStudyTypeId() != null)
                specialist.setStudyType(studyTypeRepository.findById(specialistPayload.getStudyTypeId()).orElseThrow(
                        () -> new ResourceNotFound("study type", "id", specialistPayload.getStudyTypeId())
                ));

            specialistRepository.save(specialist);
            return Result.success(specialist);
        } catch (Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public Result deleteSpecialist(UUID id) {
        try {
            Specialist specialist = specialistRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFound("specialist", "id", id)
            );
            specialistRepository.deleteById(id);
            new File(String.format("%s/%s.%s", specialist.getImage().getUploadPath(), specialist.getImage().getHashId(), specialist.getImage().getExtension())).delete();

            return Result.message("successful deleted", true);
        } catch (Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public Result getAll() {
        return Result.success(specialistRepository.findAll(Sort.by("createdAt")));
    }

    @Override
    public Result findById(UUID id) {
        try {
            Specialist specialist = specialistRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFound("specialist", "id", id)
            );
            return Result.success(specialist);
        } catch (Exception e) {
            return Result.exception(e);
        }
    }
}
