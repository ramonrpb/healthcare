package br.com.healthcare.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.healthcare.domain.Exam;
import br.com.healthcare.domain.HealthcareInstitution;
import br.com.healthcare.services.ExamService;
import br.com.healthcare.services.HealthcareInstitutionService;

@RestController
@RequestMapping(value = "/healthcare-institution")
@CrossOrigin
public class HealthcareInstitutionController {

	@Autowired
	private HealthcareInstitutionService service;
	
	@Autowired
	private ExamService examService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<HealthcareInstitution> find(@PathVariable Long id) {
		HealthcareInstitution healthcareInstitution = service.find(id);
		return ResponseEntity.ok().body(healthcareInstitution);
	}
	
	@RequestMapping(value="/healthcareInstitution/{id}/exam/{examId}", method=RequestMethod.GET)
	public ResponseEntity<Exam> find(@PathVariable Long id, @PathVariable Long examId) {
		Exam exam = examService.findHealthcareInstitutionExam(id, examId);
		return ResponseEntity.ok().body(exam);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody HealthcareInstitution healthcareInstitution) {
		healthcareInstitution = service.insert(healthcareInstitution);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(healthcareInstitution.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody HealthcareInstitution healthcareInstitution, @PathVariable Long id) {
		healthcareInstitution.setId(id);
		healthcareInstitution = service.update(healthcareInstitution);
		return ResponseEntity.noContent().build();
	}
	
}
