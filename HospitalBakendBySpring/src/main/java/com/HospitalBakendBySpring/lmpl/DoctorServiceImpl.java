package com.HospitalBakendBySpring.lmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HospitalBakendBySpring.Repository.DoctorRepository;
import com.HospitalBakendBySpring.Repository.PatientRepository;
import com.HospitalBakendBySpring.dto.DoctorDto;
import com.HospitalBakendBySpring.exception.DuplicateEmailException;
import com.HospitalBakendBySpring.exception.ResourceNotFoundException;
import com.HospitalBakendBySpring.mapper.DoctorMapper;
import com.HospitalBakendBySpring.model.Doctor;
import com.HospitalBakendBySpring.model.Patient;
import com.HospitalBakendBySpring.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {
	
	@Autowired
	private PatientRepository patientRepository;

	private final DoctorRepository doctorRepository;
	
	
	public DoctorServiceImpl(DoctorRepository doctorRepository) {
		super();
		this.doctorRepository = doctorRepository;
	}
	

	@Override
	public DoctorDto createDoctor(DoctorDto doctorDto) {
		if(doctorRepository.existsByEmail(doctorDto.getEmail()))
		{
			throw new DuplicateEmailException("Email is already in use "+doctorDto.getEmail());
		}
		
		Doctor doctor = DoctorMapper.mapToDoctor(doctorDto);
		Doctor savedDoctor = doctorRepository.save(doctor);
		return DoctorMapper.mapToDoctorDto(savedDoctor);
	}

	@Override
	public DoctorDto getById(long id) {
		Doctor d = doctorRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Doctor with ID " + id + " not found"));
		return DoctorMapper.mapToDoctorDto(d);
	}

	@Override
	public DoctorDto updateDoctor(DoctorDto doctorDto) {
		Doctor existingDoctor = doctorRepository.findById(doctorDto.getId())
				.orElseThrow(()->new ResourceNotFoundException("Doctor not found with ID: " + doctorDto.getId()));
		
		existingDoctor.setFirstName(doctorDto.getFirstName());
		existingDoctor.setLastName(doctorDto.getLastName());
		existingDoctor.setEmail(doctorDto.getEmail());
		existingDoctor.setPhone(doctorDto.getPhone());
		existingDoctor.setDepartment(doctorDto.getDepartment());
		existingDoctor.setExperienceYears(doctorDto.getExperienceYears());
		existingDoctor.setGender(doctorDto.getGender());
		existingDoctor.setQualification(doctorDto.getQualification());
		existingDoctor.setSpecialization(doctorDto.getSpecialization());
		
		Doctor updatedDoctor = doctorRepository.save(existingDoctor);
		
		return DoctorMapper.mapToDoctorDto(updatedDoctor);
				
	}

	@Override
	public void deleteDoctor(long id) {
		Doctor existingDoctor = doctorRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Doctor not found with ID: " + id));
		doctorRepository.delete(existingDoctor);
		
	}

	@Override
	public List<DoctorDto> findDoctorByfirstNameIngnoreCase(String fname) {
		
		List<Doctor> doctors = doctorRepository.findByfirstNameIgnoreCase(fname);
		
		return DoctorMapper.mapToDoctorDtoList(doctors);
	}

	@Override
	public List<DoctorDto> findAllDoctors() {
		List<Doctor> doctors = doctorRepository.findAll();
		return DoctorMapper.mapToDoctorDtoList(doctors);
	}


	@Override
	public DoctorDto updateDoctorById(long id, DoctorDto doctorDto) {
	    Doctor existingDoctor = doctorRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + id));

	    existingDoctor.setFirstName(doctorDto.getFirstName());
	    existingDoctor.setLastName(doctorDto.getLastName());
	    existingDoctor.setEmail(doctorDto.getEmail());
	    existingDoctor.setPhone(doctorDto.getPhone());
	    existingDoctor.setDepartment(doctorDto.getDepartment());
	    existingDoctor.setExperienceYears(doctorDto.getExperienceYears());
	    existingDoctor.setGender(doctorDto.getGender());
	    existingDoctor.setQualification(doctorDto.getQualification());
	    existingDoctor.setSpecialization(doctorDto.getSpecialization());
	    if (doctorDto.getPatientIds() != null && !doctorDto.getPatientIds().isEmpty()) {
	        List<Patient> patients = patientRepository.findAllById(doctorDto.getPatientIds());
	        existingDoctor.setPatients(patients);
	    } else {
	        existingDoctor.setPatients(null); // or keep old patients if desired
	    }

	    Doctor updatedDoctor = doctorRepository.save(existingDoctor);
	    return DoctorMapper.mapToDoctorDto(updatedDoctor);
	}


	@Override
	public List<DoctorDto> findByDepartmentIngnoreCase(String dname) {
		List<Doctor> doctors = doctorRepository.findByDepartmentIgnoreCase(dname);
		
		return DoctorMapper.mapToDoctorDtoList(doctors);
	}

}
