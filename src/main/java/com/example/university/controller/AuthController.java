package com.example.cafeservice.controller;

import com.example.cafeservice.entity.Parent;
import com.example.cafeservice.payload.LoginPayload;
import com.example.cafeservice.repository.AdminRepository;
import com.example.cafeservice.repository.ParentRepository;
import com.example.cafeservice.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AdminRepository adminRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final ParentRepository parentRepository;




    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginPayload payload)
    {
        System.out.println(payload);

        Parent parent=parentRepository.findByUserName(payload.getUserName());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payload.getUserName(),payload.getPassword()));
        String token=jwtTokenProvider.createToken(parent.getUserName(),parent.getRoles());

        if (token==null)
        {
            return new ResponseEntity("Xatolik", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Map<String,Object> login=new HashMap<>();
        login.put("token",token);
        login.put("username",parent.getUserName());
        login.put("success",true);
        return ResponseEntity.ok(login);


    }
}
