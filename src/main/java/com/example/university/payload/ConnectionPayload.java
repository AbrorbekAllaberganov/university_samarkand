package com.example.university.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConnectionPayload {
    String fullName;
    String phoneNumber;
    String message;
}
