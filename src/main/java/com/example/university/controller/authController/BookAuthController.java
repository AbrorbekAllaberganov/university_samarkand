package com.example.university.controller.authController;

import com.example.university.entity.Book;
import com.example.university.payload.BookPayload;
import com.example.university.payload.Result;
import com.example.university.service.impl.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth/book")
@RequiredArgsConstructor
@CrossOrigin(value = "*", maxAge = 3600L)
public class BookAuthController {

    private final BookServiceImpl bookServiceImpl;

    @GetMapping
    public ResponseEntity<Page<Book>> getAllBooks(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(bookServiceImpl.getBooksWithPageable(page, size));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Page<Book>> getAllBooksByCategoryId(@PathVariable UUID categoryId,
                                                              @RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(bookServiceImpl.getBooksWithPageableByCategoryId(page, size,categoryId));
    }
}
