package com.HospitalBakendBySpring.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "doctors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String specialization;

    @Column(nullable = false)
    private String gender;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(name = "experience_years")
    private int experienceYears;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "department")
    private String department;
    
    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    private List<Patient> patients;
}
