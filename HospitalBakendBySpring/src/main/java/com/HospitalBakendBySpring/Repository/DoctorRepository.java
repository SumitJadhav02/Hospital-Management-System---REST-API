package com.HospitalBakendBySpring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HospitalBakendBySpring.model.Doctor;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	List<Doctor> findByfirstNameIgnoreCase(String name);
	boolean existsByEmail(String email);
	List<Doctor> findByDepartmentIgnoreCase(String dname);
}
