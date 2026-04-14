package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobResponse {

    private int id;
    private String companyName;
    private String role;
    private String status;
    private float ctc;
    private String appliedDate;
    private String notes;
}