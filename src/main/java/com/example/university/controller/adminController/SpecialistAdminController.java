package com.example.university.controller.adminController;

import com.example.university.payload.Result;
import com.example.university.payload.SpecialistPayload;
import com.example.university.service.impl.SpecialistServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/speacialist")
@RequiredArgsConstructor
@CrossOrigin(value = "*", maxAge = 3600L)
public class SpecialistAdminController {
    private final SpecialistServiceImpl specialistService;

    @PostMapping
    public ResponseEntity<Result> saveSpecialist(@RequestBody SpecialistPayload specialistPayload){
        Result result=specialistService.saveSpecialist(specialistPayload);
        return ResponseEntity.status(result.isStatus()?200:409).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> editSpecialist(@PathVariable UUID id,@RequestBody SpecialistPayload specialistPayload){
        Result result=specialistService.editSpecialist(id,specialistPayload);
        return ResponseEntity.status(result.isStatus()?200:409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteSpecialist(@PathVariable UUID id){
        Result result=specialistService.deleteSpecialist(id);
        return ResponseEntity.status(result.isStatus()?200:409).body(result);
    }

}
