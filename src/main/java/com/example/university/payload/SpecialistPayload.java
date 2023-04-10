package com.example.university.payload;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class SpecialistPayload {
    String nameUz;
    String nameRu;
    String nameEn;

    String bodyUz;
    String bodyRu;
    String bodyEn;

    String kvalifikatsiyaUz;
    String kvalifikatsiyaRu;
    String kvalifikatsiyaEn;

    Integer studyDuration;
    Integer studyTypeId;
    String imageHashId;
    List<DepartmentPayload> departments;
}
