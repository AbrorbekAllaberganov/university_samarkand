package com.example.university.controller.authController;

import com.example.university.entity.Parent;
import com.example.university.payload.ConnectionPayload;
import com.example.university.payload.LoginPayload;
import com.example.university.payload.Result;
import com.example.university.payload.StudentPayload;
import com.example.university.repository.AdminRepository;
import com.example.university.repository.ParentRepository;
import com.example.university.repository.StudentRepository;
import com.example.university.security.JwtTokenProvider;
import com.example.university.service.StudentService;
import com.example.university.service.impl.ConnectionServiceImpl;
import com.example.university.service.impl.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(value = "*", maxAge = 3600L)
public class AuthController {

    private final AdminRepository adminRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final ParentRepository parentRepository;
    private final ConnectionServiceImpl connectionService;

    private final StudentServiceImpl studentService;
    private final StudentRepository studentRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginPayload payload) {
        Parent parent = parentRepository.findByUserName(payload.getUserName());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payload.getUserName(), payload.getPassword()));
        String token = jwtTokenProvider.createToken(parent.getUserName(), parent.getRoles());

        if (token == null) {
            return new ResponseEntity<>("Xatolik", HttpStatus.UNAUTHORIZED);
        }

        Map<String, Object> login = new HashMap<>();
        login.put("token", token);
        login.put("username", parent.getUserName());
        login.put("success", true);
        return ResponseEntity.ok(login);


    }

    @PostMapping("/send-message")
    public ResponseEntity<Result> sendMessage(@RequestBody ConnectionPayload connectionPayload) {
        Result result = connectionService.saveConnection(connectionPayload);
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }

    @PostMapping("/register")
    public ResponseEntity<Result> registerStudent(@RequestBody StudentPayload studentPayload) {
        Result result = studentService.saveStudent(studentPayload);
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }

    @GetMapping("/study-type")
    public ResponseEntity<Result> getAllStudyType(){
        Result result=studentService.getStudyType();
        return ResponseEntity.ok(result);
    }

}

