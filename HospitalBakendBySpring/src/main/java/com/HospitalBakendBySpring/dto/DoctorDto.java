package com.HospitalBakendBySpring.dto;

import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String specialization;
    private String gender;
    private String email;
    private String phone;
    private int experienceYears;
    private String qualification;
    private String department;
    private List<Long> patientIds;
}

