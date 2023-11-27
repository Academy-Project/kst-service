package com.academyproject.registration.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean status;

    @Column(nullable = false)
    private Integer sks;

    public Subject(String name, String code, Boolean status, Integer sks) {
        this.name = name;
        this.code = code;
        this.status = status;
        this.sks = sks;
    }
}
