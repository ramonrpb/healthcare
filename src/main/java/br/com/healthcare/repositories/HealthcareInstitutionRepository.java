package br.com.healthcare.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import br.com.healthcare.domain.HealthcareInstitution;

@Repository
public interface HealthcareInstitutionRepository extends JpaRepositoryImplementation<HealthcareInstitution, Long> {

	HealthcareInstitution findByCnpj(String cnpj);

}
