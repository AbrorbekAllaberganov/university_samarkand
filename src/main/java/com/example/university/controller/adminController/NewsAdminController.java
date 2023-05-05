package com.example.university.controller.adminController;

import com.example.university.payload.NewsPayload;
import com.example.university.payload.Result;
import com.example.university.repository.MyFileRepository;
import com.example.university.repository.NewsRepository;
import com.example.university.repository.RoleRepository;
import com.example.university.service.impl.NewsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/news")
@RequiredArgsConstructor
@CrossOrigin(value = "*", maxAge = 3600L)
public class NewsAdminController {
    private final NewsServiceImpl newsService;

    @PostMapping
    public ResponseEntity<Result> saveNews(@RequestBody NewsPayload newsPayload){
        Result result=newsService.saveNews(newsPayload);
        return ResponseEntity.status(result.isStatus()?200:409).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> editNews(@PathVariable UUID id,@RequestBody NewsPayload newsPayload){
        Result result=newsService.editNews(id,newsPayload);
        return ResponseEntity.status(result.isStatus()?200:409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteNews(@PathVariable UUID id){
        Result result=newsService.deleteNews(id);
        return ResponseEntity.status(result.isStatus()?200:409).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findNewsById(@PathVariable UUID id){
        Result result=newsService.findById(id);
        return ResponseEntity.status(result.isStatus()?200:409).body(result);
    }
}
