package com.example.university.payload;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentPayload {
    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    UUID facultyId;
    Integer studyTypeId;
    String passportHashId;
    String attestatHashId;
}
