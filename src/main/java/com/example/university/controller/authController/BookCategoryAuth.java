package com.example.university.controller.authController;

import com.example.university.payload.Result;
import com.example.university.repository.RoleRepository;
import com.example.university.service.BookCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth/category")
@RequiredArgsConstructor
@CrossOrigin(value = "*", maxAge = 3600L)
public class BookCategoryAuth {
    private final BookCategoryService bookCategoryService;

    @GetMapping
    public ResponseEntity<Result> getCategories(){
        Result result=bookCategoryService.getAll();
        return ResponseEntity.status(result.isStatus()?200:409).body(result);
    }



}
