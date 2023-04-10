package com.example.university.loader;

import com.example.university.entity.Admin;
import com.example.university.entity.Parent;
import com.example.university.entity.Role;
import com.example.university.entity.StudyType;
import com.example.university.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String init;

    private final RoleRepository roleRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final ParentRepository parentRepository;
    private final StudentRepository studentRepository;
    private final StudyTypeRepository studyTypeRepository;

    @Override
    public void run(String... args) throws Exception {


        try {
            if (init.equalsIgnoreCase("create")) {
                Role roleUser = new Role();
                roleUser.setId(1L);
                roleUser.setName("ROLE_USER");

                Role roleAdmin = new Role();
                roleAdmin.setId(2L);
                roleAdmin.setName("ROLE_ADMIN");

                List<Role> roleList = new ArrayList<>(Arrays.asList(roleUser, roleAdmin));
                roleList = roleRepository.saveAll(roleList);

                Admin admin = new Admin();
                Parent parent = new Parent();
                parent.setRoles(new ArrayList<>(Arrays.asList(roleRepository.findByName("ROLE_ADMIN"))));
                parent.setUserName("admin");
                parent.setPassword(passwordEncoder.encode("111"));
                parent.setFullName("Abror Allaberganov");
                parent.setPhoneNumber("+998977777777");
                parentRepository.save(parent);
                admin.setParent(parent);

                adminRepository.save(admin);

                StudyType studyType1=new StudyType();
                studyType1.setId(1);
                studyType1.setNameUz("Kunduzgi");
                studyType1.setNameRu("Kunduzgi Ru");
                studyType1.setNameEn("Kunduzgi En");

                StudyType studyType2=new StudyType();
                studyType2.setId(2);
                studyType2.setNameUz("Sirtqi");
                studyType2.setNameRu("Sirtqi Ru");
                studyType2.setNameEn("Sirtqi En");

                StudyType studyType3=new StudyType();
                studyType3.setId(3);
                studyType3.setNameUz("Kechgi");
                studyType3.setNameRu("Kechgi Ru");
                studyType3.setNameEn("Kechgi En");

                studyTypeRepository.saveAll(Arrays.asList(studyType1,studyType2,studyType3));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
