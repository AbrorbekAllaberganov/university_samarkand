package com.example.university.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class News {
    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    protected UUID id;

    @Column(nullable = false)
    String titleUz;
    @Column(nullable = false)
    String titleRu;
    @Column(nullable = false)
    String titleEn;

    @Column(nullable = false, length = 1023)
    String bodyUz;

    @Column(nullable = false, length = 1023)
    String bodyRu;

    @Column(nullable = false, length = 1023)
    String bodyEn;

    @OneToOne
    MyFile image;

    @CreationTimestamp
    Date createdAt;

    @UpdateTimestamp
    Date updatedAt;
}
