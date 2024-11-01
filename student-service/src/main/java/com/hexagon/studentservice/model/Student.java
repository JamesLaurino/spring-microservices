package com.hexagon.studentservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "students")
public class Student {
    @Id
    private String id;
    private String name;
    private int age;
    private String gender;
    private Integer schoolId;
}
