package com.example.university.service.impl;

import com.example.university.exceptions.BadRequest;
import com.example.university.payload.Result;
import com.example.university.repository.AdminRepository;
import com.example.university.repository.ConnectionRepository;
import com.example.university.repository.ParentRepository;
import com.example.university.security.SecurityUtils;
import com.example.university.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final SecurityUtils securityUtils;
    private final AdminRepository adminRepository;


    @Override
    public Result getMe() {
        try{
            Optional<String> currentUser = securityUtils.getCurrentUser();
            if (!currentUser.isPresent())
                throw new BadRequest("User not found");
            return Result.success(adminRepository.findByParent_UserName(currentUser.get()));
        }catch (Exception e){
            return Result.exception(e);
        }
    }
}
