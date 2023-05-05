package com.example.university.controller.adminController;

import com.example.university.payload.FacultyPayload;
import com.example.university.payload.Result;
import com.example.university.service.impl.FacultyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/faculty")
@RequiredArgsConstructor
@CrossOrigin(value = "*", maxAge = 3600L)
public class FacultyAdminController {
    private final FacultyServiceImpl facultyServiceImpl;

    @PostMapping
    public ResponseEntity<Result> saveFaculty(@RequestBody FacultyPayload facultyPayload){
        Result result=facultyServiceImpl.saveFaculty(facultyPayload);
        return ResponseEntity.status(result.isStatus()?200:409).body(result);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Result> editFaculty(@PathVariable UUID id,@RequestBody FacultyPayload facultyPayload){
        Result result=facultyServiceImpl.editFaculty(id,facultyPayload);
        return ResponseEntity.status(result.isStatus()?200:409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteFaculty(@PathVariable UUID id){
        Result result=facultyServiceImpl.deleteFaculty(id);
        return ResponseEntity.status(result.isStatus()?200:409).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> findFacultyById(@PathVariable UUID id){
        Result result=facultyServiceImpl.findNewsBoyId(id);
        return ResponseEntity.status(result.isStatus()?200:409).body(result);
    }

}
