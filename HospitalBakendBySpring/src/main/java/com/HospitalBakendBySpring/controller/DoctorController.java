package com.HospitalBakendBySpring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HospitalBakendBySpring.dto.DoctorDto;
import com.HospitalBakendBySpring.lmpl.DoctorServiceImpl;

@RestController
@RequestMapping("api/doctors")
public class DoctorController {

	private final DoctorServiceImpl doctorServiceImpl;

	public DoctorController(DoctorServiceImpl doctorServiceImpl) {
		super();
		this.doctorServiceImpl = doctorServiceImpl;
	}
	
	@PostMapping
	public ResponseEntity<DoctorDto> creatingDoctor(@RequestBody DoctorDto doctorDto)
	{
		DoctorDto d =doctorServiceImpl.createDoctor(doctorDto);
		return new ResponseEntity<>(d,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<DoctorDto>> getAllDoctors()
	{
		List<DoctorDto>doctors =doctorServiceImpl.findAllDoctors();
		return ResponseEntity.ok(doctors);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<DoctorDto> getById(@PathVariable("id") long id)
	{
		DoctorDto d = doctorServiceImpl.getById(id);
		
		return ResponseEntity.ok(d);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<DoctorDto>> getByFirstName(@RequestParam("fname") String fname)
	{
		List<DoctorDto> doctors = doctorServiceImpl.findDoctorByfirstNameIngnoreCase(fname);
		
		return ResponseEntity.ok(doctors);
	}
	
	@PutMapping
	public ResponseEntity<DoctorDto> updateDoctor(@RequestBody DoctorDto doctorDto)
	{
		DoctorDto updatedDoctor = doctorServiceImpl.updateDoctor(doctorDto);
		
		return ResponseEntity.ok(updatedDoctor);
	}
	
	
	@PutMapping("{id}")
	public ResponseEntity<DoctorDto> updateDoctorById(@PathVariable("id") long id ,@RequestBody DoctorDto doctorDto)
	{
		DoctorDto updatedDoctor = doctorServiceImpl.updateDoctorById(id, doctorDto);
		
		return ResponseEntity.ok(updatedDoctor);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteDoctor(@PathVariable("id") long id)
	{
		doctorServiceImpl.deleteDoctor(id);
		
		return ResponseEntity.ok("Doctor Deletes Successfully");
	}
	
	@GetMapping("/filter")
	public ResponseEntity<List<DoctorDto>> findByDoctorsDepartment(@RequestParam("dept") String dept)
	{
		List<DoctorDto> d = doctorServiceImpl.findByDepartmentIngnoreCase(dept);
		return ResponseEntity.ok(d);
	}
}
