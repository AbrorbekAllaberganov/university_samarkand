package com.example.university.controller.authController;

import com.example.university.payload.Result;
import com.example.university.service.impl.SpecialistServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth/specialist")
@RequiredArgsConstructor
@CrossOrigin(value = "*", maxAge = 3600L)
public class SpecialistAuthController {
    private final SpecialistServiceImpl specialistService;

    @GetMapping("/{id}")
    public ResponseEntity<Result> findSpecialistById(@PathVariable UUID id){
        Result result=specialistService.findById(id);
        return ResponseEntity.status(result.isStatus()?200:409).body(result);
    }


    @GetMapping
    public ResponseEntity<Result> getAllSpecialist(){
        Result result=specialistService.getAll();
        return ResponseEntity.status(result.isStatus()?200:409).body(result);
    }

}
