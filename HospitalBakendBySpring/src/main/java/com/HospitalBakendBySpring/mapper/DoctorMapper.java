package com.HospitalBakendBySpring.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.HospitalBakendBySpring.dto.DoctorDto;
import com.HospitalBakendBySpring.model.Doctor;
import com.HospitalBakendBySpring.model.Patient;

public class DoctorMapper {

    // Convert Doctor → DoctorDto
    public static DoctorDto mapToDoctorDto(Doctor doctor) {
        List<Long> patientIds = null;

        if (doctor.getPatients() != null) {
            patientIds = doctor.getPatients()
                               .stream()
                               .map(Patient::getId)
                               .collect(Collectors.toList());
        }

        return DoctorDto.builder()
                .id(doctor.getId())
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .specialization(doctor.getSpecialization())
                .email(doctor.getEmail())
                .department(doctor.getDepartment())
                .experienceYears(doctor.getExperienceYears())
                .phone(doctor.getPhone())
                .gender(doctor.getGender())
                .qualification(doctor.getQualification())
                .patientIds(patientIds) // ✅ add patientIds to DTO
                .build();
    }

    // Convert DoctorDto → Doctor (patients set separately in service layer)
    public static Doctor mapToDoctor(DoctorDto doctorDto) {
        return Doctor.builder()
                .id(doctorDto.getId())
                .firstName(doctorDto.getFirstName())
                .lastName(doctorDto.getLastName())
                .specialization(doctorDto.getSpecialization())
                .email(doctorDto.getEmail())
                .department(doctorDto.getDepartment())
                .experienceYears(doctorDto.getExperienceYears())
                .phone(doctorDto.getPhone())
                .gender(doctorDto.getGender())
                .qualification(doctorDto.getQualification())
                .build(); // patients will be set later
    }

    // Convert list of Doctor → list of DoctorDto
    public static List<DoctorDto> mapToDoctorDtoList(List<Doctor> doctors) {
        return doctors.stream()
                .map(DoctorMapper::mapToDoctorDto)
                .collect(Collectors.toList());
    }
}
