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
public class BookPayload {
    String nameUz;
    String nameRu;
    String nameEn;

    String fileHashId;
    String imageHashId;
    UUID categoryId;
}
