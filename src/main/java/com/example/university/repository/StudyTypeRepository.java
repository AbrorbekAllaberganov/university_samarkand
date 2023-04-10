package com.example.university.repository;

import com.example.university.entity.StudyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface StudyTypeRepository extends JpaRepository<StudyType, Integer> {
}
