package com.example.university.service;

import com.example.university.entity.BookCategory;
import com.example.university.payload.BookCategoryPayload;
import com.example.university.payload.Result;

import java.util.UUID;

public interface BookCategoryService {
    Result saveCategory(BookCategoryPayload categoryPayload);

    Result editCategory(UUID categoryId, BookCategoryPayload categoryPayload);

    Result deleteCategory(UUID categoryId);

    Result getAll();
    BookCategory findBookCategoryById(UUID categoryId);
}
