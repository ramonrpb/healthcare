package br.com.healthcare.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import br.com.healthcare.domain.HealthcareInstitution;
import br.com.healthcare.exceptions.ObjectNotFoundException;
import br.com.healthcare.repositories.HealthcareInstitutionRepository;

@Service
public class HealthcareInstitutionService {

	@Autowired
	private HealthcareInstitutionRepository repository;

	public HealthcareInstitution find(Long id) {
		Optional<HealthcareInstitution> obj = ((CrudRepository<HealthcareInstitution, Long>) repository).findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Instituição não encontrada! Id: " + id + ", Tipo: " + HealthcareInstitution.class.getName()));
	}

	public @Valid HealthcareInstitution insert(@Valid HealthcareInstitution healthcareInstitution) {
		
		healthcareInstitution.setCoins(20);
		healthcareInstitution = repository.save(healthcareInstitution);
		return healthcareInstitution;
	}

	public @Valid HealthcareInstitution update(@Valid HealthcareInstitution healthcareInstitution) {
		
		if(repository.existsById(healthcareInstitution.getId())) {
			HealthcareInstitution institution = find(healthcareInstitution.getId());
			institution.setCnpj(healthcareInstitution.getCnpj());
			institution.setName(healthcareInstitution.getName());
			healthcareInstitution = repository.save(institution);
		}else {
			throw new ObjectNotFoundException("Instituição não encontrada! Id: " + healthcareInstitution.getId() + ", Tipo: " + HealthcareInstitution.class.getName());
		}
		return healthcareInstitution;
	}
	
}
