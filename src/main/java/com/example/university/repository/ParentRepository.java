package com.example.university.repository;


import com.example.university.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParentRepository extends JpaRepository<Parent, UUID> {
    Parent findByUserName (String username);

}
