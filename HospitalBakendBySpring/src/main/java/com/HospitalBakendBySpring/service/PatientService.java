package com.HospitalBakendBySpring.service;

import com.HospitalBakendBySpring.dto.PatientDto;
import java.util.List;

public interface PatientService {

    PatientDto createPatient(PatientDto patientDto);
    PatientDto updatePatient(PatientDto patientDto);
    PatientDto updatePatientById(long id ,PatientDto patientDto);

    PatientDto getPatientById(long id);
    
    List<PatientDto> findByFirstNameIgnoreCase(String firstName);

    List<PatientDto> getAllPatients();

    void deletePatient(long id);
    public List<PatientDto> getPatientsByDoctorId(Long doctorId);
    
}