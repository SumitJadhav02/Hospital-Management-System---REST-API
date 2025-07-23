package com.HospitalBakendBySpring.mapper;

import java.util.List;
import java.util.stream.Collectors;
import com.HospitalBakendBySpring.dto.PatientDto;
import com.HospitalBakendBySpring.model.Patient;

public class PatientMapper {

	public static PatientDto mapToPatientDto(Patient patient) {
	    return PatientDto.builder()
	            .id(patient.getId())
	            .doctorId(patient.getDoctor() != null ? patient.getDoctor().getId() : null) // ✅ include this
	            .firstName(patient.getFirstName())
	            .lastName(patient.getLastName())
	            .gender(patient.getGender())
	            .dateOfBirth(patient.getDateOfBirth())
	            .email(patient.getEmail())
	            .phone(patient.getPhone())
	            .address(patient.getAddress())
	            .bloodGroup(patient.getBloodGroup())
	            .medicalHistory(patient.getMedicalHistory())
	            .build();
	}
	
	public static Patient mapToPatient(PatientDto dto)
	{
		return Patient.builder()
				.id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .gender(dto.getGender())
                .dateOfBirth(dto.getDateOfBirth())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .bloodGroup(dto.getBloodGroup())
                .medicalHistory(dto.getMedicalHistory())
                .build();
	}
	
	 public static List<PatientDto> mapToPatientDtoList(List<Patient> patients) {
	        return patients.stream()
	                .map(PatientMapper::mapToPatientDto)
	                .collect(Collectors.toList());
	    }
}
