package com.example.university.service;

import com.example.university.payload.Result;
import com.example.university.payload.SpecialistPayload;

import java.util.UUID;

public interface SpecialistService {
    Result saveSpecialist(SpecialistPayload specialistPayload);
    Result editSpecialist(UUID id,SpecialistPayload specialistPayload);
    Result deleteSpecialist(UUID id);
    Result getAll();

    Result findById(UUID id);
}
