package net.guides.springboot2.springboot2jpacrudexample.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.guides.springboot2.springboot2jpacrudexample.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2jpacrudexample.model.Education;
import net.guides.springboot2.springboot2jpacrudexample.repository.EducationRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EducationController {
	@Autowired
	private EducationRepository educationRepository;

	@GetMapping("/employees")
	public List<Education> getAllEmployees() {
		return educationRepository.findAll();
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Education> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Education education = educationRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(education);
	}

	@PostMapping("/employees")
	public Education createEmployee(@Valid @RequestBody Education employee) {
		return educationRepository.save(employee);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Education> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Education employeeDetails) throws ResourceNotFoundException {
		Education education = educationRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		education.setEmailId(employeeDetails.getEmailId());
		education.setLastName(employeeDetails.getLastName());
		education.setFirstName(employeeDetails.getFirstName());
		final Education updatedEmployee = educationRepository.save(education);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long educationId)
			throws ResourceNotFoundException {
		Education education = educationRepository.findById(educationId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + educationId));

		educationRepository.delete(education);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
