package br.com.healthcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.healthcare.repositories.HealthcareInstitutionRepository;

@Service
public class HealthcareInstitutionService {

	@Autowired
	private HealthcareInstitutionRepository repository;
	
}
