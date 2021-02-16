package br.com.healthcare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.healthcare.services.HealthcareInstitutionService;

@RestController
@RequestMapping(value = "/healthcare-institution")
public class HealthcareInstitutionController {

	@Autowired
	private HealthcareInstitutionService service;
	
}
