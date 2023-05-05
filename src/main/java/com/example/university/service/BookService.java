package com.example.university.service;

import com.example.university.entity.Book;
import com.example.university.payload.BookPayload;
import com.example.university.payload.Result;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface BookService {
    Result saveBook(BookPayload bookPayload);

    Result editBook(UUID bookId, BookPayload bookPayload);

    Result deleteBook(UUID bookId);

    Page<Book> getBooksWithPageable(int pageSize, int pageNo);

    Page<Book> getBooksWithPageableByCategoryId(int pageSize, int pageNo,UUID categoryId);

    Result findBookById(UUID bookId);

}
