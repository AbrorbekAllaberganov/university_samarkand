package com.example.university.repository;

import com.example.university.entity.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpecialistRepository extends JpaRepository<Specialist, UUID> {
}
