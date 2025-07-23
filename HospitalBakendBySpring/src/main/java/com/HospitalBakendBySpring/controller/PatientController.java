package com.HospitalBakendBySpring.controller;


import java.util.List;

import org.springframework.data.repository.query.Param;
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

import com.HospitalBakendBySpring.dto.PatientDto;
import com.HospitalBakendBySpring.lmpl.PatientServiceImpl;
import com.HospitalBakendBySpring.model.Patient;

@RestController
@RequestMapping("api/patients")
public class PatientController {
	
	private final PatientServiceImpl patientServiceImpl;
	
	

	public PatientController(PatientServiceImpl patientServiceImpl) {
		super();
		this.patientServiceImpl = patientServiceImpl;
	}


    @PostMapping
	public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto)
	{
    	
    	
		PatientDto p =patientServiceImpl.createPatient(patientDto);
		return new ResponseEntity<>(p,HttpStatus.CREATED);
	}
    
    @GetMapping("{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable("id") long id)
    {
    	PatientDto patientDto  = patientServiceImpl.getPatientById(id);
    	
    	return ResponseEntity.ok(patientDto);
    }
    
    
    //http://localhost:8080/api/patients/search?fname=	
    @GetMapping("/search")
    public ResponseEntity<List<PatientDto>> findPatientsByFirstName(@RequestParam("fname") String firstName) {
        List<PatientDto> patients = patientServiceImpl.findByFirstNameIgnoreCase(firstName);
        return ResponseEntity.ok(patients);
    }
    
    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients()
    {
    	List<PatientDto> patients = patientServiceImpl.getAllPatients();
    	return ResponseEntity.ok(patients);
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePatientById(@PathVariable("id") long id)
    {
    	patientServiceImpl.deletePatient(id);
    	return ResponseEntity.ok("Patient deleted successfully");
    }
    
    @PutMapping
    public ResponseEntity<PatientDto> updatePatient(@RequestBody PatientDto patientDto)
    {
    	PatientDto updated = patientServiceImpl.updatePatient(patientDto);
    	
    	return new ResponseEntity<>(updated,HttpStatus.OK);	
    }
    
    
    @PutMapping("{id}")
    public ResponseEntity<PatientDto> updatePatientById(@PathVariable("id") long id, @RequestBody PatientDto patientDto)
    {
    	PatientDto updated = patientServiceImpl.updatePatientById(id, patientDto);
    	
    	return new ResponseEntity<>(updated,HttpStatus.OK);	
    }
    
    @GetMapping("/doctor/{did}")
    public ResponseEntity<List<PatientDto>> findPatientsByDoctorId(@PathVariable("did") long did)
    {
    	List<PatientDto> patients = patientServiceImpl.getPatientsByDoctorId(did);
    	
    	return ResponseEntity.ok(patients);
    }
	
}
