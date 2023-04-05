package com.example.university.service;

import com.example.university.payload.FacultyPayload;
import com.example.university.payload.Result;

import java.util.UUID;

public interface FacultyService {
    Result saveFaculty(FacultyPayload facultyPayload);

    Result editFaculty(UUID facultyId, FacultyPayload facultyPayload);

    Result deleteFaculty(UUID facultyId);

    Result getAll();
}
