package com.example.university.controller.adminController;

import com.example.university.payload.BookCategoryPayload;
import com.example.university.payload.Result;
import com.example.university.service.BookCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/category")
@RequiredArgsConstructor
@CrossOrigin(value = "*", maxAge = 3600L)
public class BookCategoryAdminController {
    private final BookCategoryService bookCategoryService;

    @PostMapping
    public ResponseEntity<Result> saveBookCategory(@RequestBody BookCategoryPayload bookCategoryPayload){
        Result result=bookCategoryService.saveCategory(bookCategoryPayload);
        return ResponseEntity.status(result.isStatus()?200:409).body(result);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Result> editBookCategory(@PathVariable UUID id,
                                                   @RequestBody BookCategoryPayload bookCategoryPayload){
        Result result=bookCategoryService.editCategory(id,bookCategoryPayload);
        return ResponseEntity.status(result.isStatus()?200:409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteCategory(@PathVariable UUID id){
        Result result=bookCategoryService.deleteCategory(id);
        return ResponseEntity.status(result.isStatus()?200:409).body(result);
    }

}
