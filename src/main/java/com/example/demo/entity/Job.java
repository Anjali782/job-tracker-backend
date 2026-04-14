package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Job {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Getter
    private String companyName;

    @Setter
    @Getter
    private String role;

    @Setter
    @Getter
    private String status;

    @Setter
    @Getter
    private float ctc;

    @Setter
    @Getter
    private String appliedDate;

    @Setter
    @Getter
    @Column(length = 1000)
    private String notes;

}
