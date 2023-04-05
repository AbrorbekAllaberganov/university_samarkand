package com.example.cafeservice.repository;

import com.example.cafeservice.entity.MyFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface MyFileRepository extends JpaRepository<MyFile, UUID> {
    boolean deleteByHashId(String hashId);
    MyFile findByHashId(String hashId);

    @Query(value ="select f.hash_id from my_file" ,nativeQuery = true)
    Page<String> getHashId(Pageable pageable);
}
