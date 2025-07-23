package com.HospitalBakendBySpring.service;

import java.util.List;

import com.HospitalBakendBySpring.dto.DoctorDto;

public interface DoctorService {

	DoctorDto createDoctor(DoctorDto doctorDto);
	DoctorDto getById(long id);
	DoctorDto updateDoctor(DoctorDto doctorDto);
	DoctorDto updateDoctorById(long id, DoctorDto doctorDto);
	void deleteDoctor(long id);
	List<DoctorDto> findDoctorByfirstNameIngnoreCase(String fname);
	List<DoctorDto> findAllDoctors();
	
	List<DoctorDto>findByDepartmentIngnoreCase(String dname);
	
}
