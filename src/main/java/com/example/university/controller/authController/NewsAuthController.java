package com.example.university.controller.authController;

import com.example.university.entity.News;
import com.example.university.service.impl.NewsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth/news")
@RequiredArgsConstructor
@CrossOrigin(value = "*", maxAge = 3600L)
public class NewsAuthController {
    private final NewsServiceImpl newsService;

    @GetMapping
    public ResponseEntity<Page<News>> getAllBooks(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(newsService.getNewsWithPageable(page,size));
    }

}
