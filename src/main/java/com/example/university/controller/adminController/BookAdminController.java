package com.example.university.controller.adminController;

import com.example.university.payload.BookPayload;
import com.example.university.payload.Result;
import com.example.university.service.BookService;
import com.example.university.service.impl.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/book")
@RequiredArgsConstructor
@CrossOrigin(value = "*", maxAge = 3600L)
public class BookAdminController {
    private final BookServiceImpl bookServiceImpl;

    @PostMapping
    public ResponseEntity<Result> saveBook(@RequestBody BookPayload bookPayload) {
        Result result = bookServiceImpl.saveBook(bookPayload);
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Result> editBook(@PathVariable UUID id,
                                           @RequestBody BookPayload bookPayload) {
        Result result = bookServiceImpl.editBook(id, bookPayload);
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteBook(@PathVariable UUID id) {
        Result result = bookServiceImpl.deleteBook(id);
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }

}
