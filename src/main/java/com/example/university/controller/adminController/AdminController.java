package com.example.university.controller.adminController;

import com.example.university.entity.Connection;
import com.example.university.entity.Student;
import com.example.university.payload.Result;
import com.example.university.repository.StudentRepository;
import com.example.university.service.impl.AdminServiceImpl;
import com.example.university.service.impl.ConnectionServiceImpl;
import com.example.university.service.impl.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(value = "*", maxAge = 3600L)
public class AdminController {
    private final ConnectionServiceImpl connectionService;
    private final StudentServiceImpl studentService;
    private final AdminServiceImpl adminService;

    @GetMapping("/message")
    public ResponseEntity<Page<Connection>> getAllMessagesWithPageable(@RequestParam(defaultValue = "0") int page,
                                                                         @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(connectionService.getConnectionWithPageable(page, size));
    }

    @DeleteMapping("/message/{id}")
    public ResponseEntity<Result> deleteMessageById(@PathVariable UUID id){
        Result result=connectionService.deleteConnection(id);
        return ResponseEntity.status(result.isStatus()?200:409).body(result);
    }

    @PutMapping("/message/{id}")
    public ResponseEntity<Result> changeIsViewById(@PathVariable UUID id){
        Result result=connectionService.changeIsView(id);
        return ResponseEntity.status(result.isStatus()?200:409).body(result);
    }

    @GetMapping("/student")
    public ResponseEntity<Page<Student>> getAllStudentWithPageable(@RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(studentService.getAllStudentWithPageable(page, size));
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<Result> changeStudentIsView(@PathVariable UUID id){
        Result result=studentService.changeStudentIsView(id);
        return ResponseEntity.status(result.isStatus()?200:409).body(result);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<Result> deleteStudent(@PathVariable UUID id){
        Result result=studentService.deleteStudent(id);
        return ResponseEntity.status(result.isStatus()?200:409).body(result);
    }

    @GetMapping
    public ResponseEntity<Result> getMe(){
        Result result= adminService.getMe();
        return ResponseEntity.status(result.isStatus()?200:409).body(result);
    }

}
