package com.example.university.repository;

import com.example.university.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    boolean existsByCategory_id(UUID categoryId);
    Page<Book> findAllByCategory_Id(UUID categoryId,Pageable pageable);
}
