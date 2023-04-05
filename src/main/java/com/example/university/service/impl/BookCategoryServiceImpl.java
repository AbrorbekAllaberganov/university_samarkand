package com.example.university.service.impl;

import com.example.university.repository.BookCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookCategoryServiceImpl  {
    private final BookCategoryRepository bookCategoryRepository;
    private final MyFileService myFileService;



}
