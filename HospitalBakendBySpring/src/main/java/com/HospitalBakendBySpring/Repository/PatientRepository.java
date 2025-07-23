package com.HospitalBakendBySpring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HospitalBakendBySpring.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	
	List<Patient> findByFirstNameIgnoreCase(String firstName);
	boolean existsByEmail(String email);
	List<Patient>findByDoctorId(Long doctorId);
}
