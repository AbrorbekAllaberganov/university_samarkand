package com.example.university.entity;

import com.example.university.payload.DepartmentPayload;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Department {
    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    protected UUID id;

    String nameUz;
    String nameRu;
    String nameEn;

    public Department(DepartmentPayload departmentPayload) {
        this.nameUz = departmentPayload.getNameUz();
        this.nameRu = departmentPayload.getNameRu();
        this.nameEn = departmentPayload.getNameEn();
    }
}
