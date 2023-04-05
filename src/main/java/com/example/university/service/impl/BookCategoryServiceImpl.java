package com.example.university.service.impl;

import com.example.university.entity.BookCategory;
import com.example.university.exceptions.BadRequest;
import com.example.university.exceptions.ResourceNotFound;
import com.example.university.payload.BookCategoryPayload;
import com.example.university.payload.Result;
import com.example.university.repository.BookCategoryRepository;
import com.example.university.repository.BookRepository;
import com.example.university.service.BookCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookCategoryServiceImpl implements BookCategoryService {
    private final BookCategoryRepository bookCategoryRepository;
    private final BookRepository bookRepository;

    @Override
    public Result saveCategory(BookCategoryPayload categoryPayload) {
        try {
            BookCategory bookCategory = new BookCategory();
            bookCategory.setNameUz(categoryPayload.getNameUz());
            bookCategory.setNameRu(categoryPayload.getNameRu());
            bookCategory.setNameEn(categoryPayload.getNameEn());

            bookCategoryRepository.save(bookCategory);
            return Result.success(bookCategory);
        } catch (Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public Result editCategory(UUID categoryId, BookCategoryPayload categoryPayload) {
        try {
            BookCategory bookCategory = findBookCategoryById(categoryId);
            bookCategory.setNameUz(categoryPayload.getNameUz());
            bookCategory.setNameRu(categoryPayload.getNameRu());
            bookCategory.setNameEn(categoryPayload.getNameEn());

            bookCategoryRepository.save(bookCategory);
            return Result.success(bookCategory);
        } catch (Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public Result deleteCategory(UUID categoryId) {
        try {
            if (!bookRepository.existsByCategory_id(categoryId)) {
                bookCategoryRepository.deleteById(categoryId);
                return Result.message("successful deleted", true);
            } else {
                return Result.exception(new BadRequest("Please delete this category's books"));
            }
        } catch (Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public Result getAll() {
        return Result.success(bookCategoryRepository.findAll(Sort.by("createdAt")));
    }

    @Override
    public BookCategory findBookCategoryById(UUID categoryId) {
        return bookCategoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFound("book category", "id", categoryId)
        );
    }

}
