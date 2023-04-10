package com.example.university.controller.authController;

import com.example.university.payload.Result;
import com.example.university.service.impl.FacultyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth/faculty")
@RequiredArgsConstructor
@CrossOrigin(value = "*", maxAge = 3600L)
public class FacultyAuthController {
    private final FacultyServiceImpl facultyServiceImpl;

    @GetMapping
    public ResponseEntity<Result> getAll(){
        Result result=facultyServiceImpl.getAll();
        return ResponseEntity.status(result.isStatus()?200:409).body(result);

    }
}
