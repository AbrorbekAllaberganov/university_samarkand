package com.example.university.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Specialist {
    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    protected UUID id;

    String nameUz;
    String nameRu;
    String nameEn;

    @Column(length = 1023)
    String bodyUz;
    @Column(length = 1023)
    String bodyRu;
    @Column(length = 1023)
    String bodyEn;

    String kvalifikatsiyaUz;
    String kvalifikatsiyaRu;
    String kvalifikatsiyaEn;

    int studyDuration;

    @ManyToOne
    StudyType studyType;

    @OneToMany(cascade = CascadeType.ALL)
    List<Department> departments;

    @OneToOne(cascade = CascadeType.REMOVE)
    MyFile image;

    @CreationTimestamp
    Date createdAt;

    @UpdateTimestamp
    Date updatedAt;
}
