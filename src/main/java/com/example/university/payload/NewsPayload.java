package com.example.university.payload;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewsPayload {
    String titleUz;
    String titleRu;
    String titleEn;

    String bodyUz;
    String bodyRu;
    String bodyEn;

    String hashId;
}
