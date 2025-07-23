package com.HospitalBakendBySpring.lmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HospitalBakendBySpring.Repository.DoctorRepository;
import com.HospitalBakendBySpring.Repository.PatientRepository;
import com.HospitalBakendBySpring.dto.PatientDto;
import com.HospitalBakendBySpring.exception.DuplicateEmailException;
import com.HospitalBakendBySpring.exception.ResourceNotFoundException;
import com.HospitalBakendBySpring.mapper.PatientMapper;
import com.HospitalBakendBySpring.model.Doctor;
import com.HospitalBakendBySpring.model.Patient;
import com.HospitalBakendBySpring.service.PatientService;

import lombok.RequiredArgsConstructor;

@Service

public class PatientServiceImpl implements PatientService {

	
	private final DoctorRepository doctorRepository;
	
    private final PatientRepository patientRepository;

    
    
    public PatientServiceImpl(DoctorRepository doctorRepository, PatientRepository patientRepository) {
		super();
		this.doctorRepository = doctorRepository;
		this.patientRepository = patientRepository;
	}

	@Override
    public PatientDto createPatient(PatientDto patientDto) {
        if (patientRepository.existsByEmail(patientDto.getEmail())) {
            throw new DuplicateEmailException("Email already in use: " + patientDto.getEmail());
        }

        Patient patient = PatientMapper.mapToPatient(patientDto);
        Patient saved = patientRepository.save(patient);
        return PatientMapper.mapToPatientDto(saved);
    }

    @Override
    public PatientDto getPatientById(long id) {
        Patient patient = patientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Patient with ID " + id + " not found"));
        return PatientMapper.mapToPatientDto(patient);
    }

    @Override
    public List<PatientDto> findByFirstNameIgnoreCase(String firstName) {
        List<Patient> patients = patientRepository.findByFirstNameIgnoreCase(firstName);
        return PatientMapper.mapToPatientDtoList(patients);
    }

    @Override
    public List<PatientDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return PatientMapper.mapToPatientDtoList(patients);
    }

    @Override
    public void deletePatient(long id) {
        Patient patient = patientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Patient with ID " + id + " not found"));
        patientRepository.delete(patient); // safer than deleteById
    }

    @Override
    public PatientDto updatePatient(PatientDto patientDto) {
        Patient existingPatient = patientRepository.findById(patientDto.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + patientDto.getId()));

        existingPatient.setFirstName(patientDto.getFirstName());
        existingPatient.setLastName(patientDto.getLastName());
        existingPatient.setGender(patientDto.getGender());
        existingPatient.setDateOfBirth(patientDto.getDateOfBirth());
        existingPatient.setEmail(patientDto.getEmail());
        existingPatient.setPhone(patientDto.getPhone());
        existingPatient.setAddress(patientDto.getAddress());
        existingPatient.setBloodGroup(patientDto.getBloodGroup());
        existingPatient.setMedicalHistory(patientDto.getMedicalHistory());

        Patient updatedPatient = patientRepository.save(existingPatient);
        return PatientMapper.mapToPatientDto(updatedPatient);
    }

	@Override
	public PatientDto updatePatientById(long id, PatientDto patientDto) {
		 Patient existingPatient = patientRepository.findById(id)
		            .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + id));
		 existingPatient.setFirstName(patientDto.getFirstName());
	        existingPatient.setLastName(patientDto.getLastName());
	        existingPatient.setGender(patientDto.getGender());
	        existingPatient.setDateOfBirth(patientDto.getDateOfBirth());
	        existingPatient.setEmail(patientDto.getEmail());
	        existingPatient.setPhone(patientDto.getPhone());
	        existingPatient.setAddress(patientDto.getAddress());
	        existingPatient.setBloodGroup(patientDto.getBloodGroup());
	        existingPatient.setMedicalHistory(patientDto.getMedicalHistory());
	       
	        //  Set the doctor from doctorId if present
	        if (patientDto.getDoctorId() != null) {
	            Doctor doctor = doctorRepository.findById(patientDto.getDoctorId())
	                    .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + patientDto.getDoctorId()));
	            existingPatient.setDoctor(doctor);
	        } else {
	            existingPatient.setDoctor(null); // Optional: handle null doctor
	        }
	        Patient updatedPatient = patientRepository.save(existingPatient);
	        return PatientMapper.mapToPatientDto(updatedPatient);
	}

	@Override
	public List<PatientDto> getPatientsByDoctorId(Long doctorId) {
		
		List<Patient> patients = patientRepository.findByDoctorId(doctorId);
		
		return PatientMapper.mapToPatientDtoList(patients);
	}
}
