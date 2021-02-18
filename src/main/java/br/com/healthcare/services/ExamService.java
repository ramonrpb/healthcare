package br.com.healthcare.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import br.com.healthcare.domain.Exam;
import br.com.healthcare.domain.HealthcareInstitution;
import br.com.healthcare.exceptions.ObjectNotFoundException;
import br.com.healthcare.repositories.ExamRepository;
import br.com.healthcare.repositories.HealthcareInstitutionRepository;

@Service
public class ExamService {

	@Autowired
	private ExamRepository repository;
	
	@Autowired
	private HealthcareInstitutionRepository institutionRepository;

	public Exam find(Long id) {
		Optional<Exam> obj = ((CrudRepository<Exam, Long>) repository).findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Exame não encontrado! Id: " + id + ", Tipo: " + Exam.class.getName()));
	}

	public @Valid Exam insert(@Valid Exam exam) {
		
		HealthcareInstitution institution = (institutionRepository.findById(exam.getHealthcareInstitution().getId())).get();
		if(institution.getCoins() > 0) {
			exam.setRead(false);
			exam = repository.save(exam);
			institution.setCoins(institution.getCoins()-1);
			institutionRepository.save(institution);
		}else {
			// TODO - criar exception para informar falta de moedas.
		}
		
		return exam;
	}

	public @Valid Exam update(@Valid Exam exam) {
		if(repository.existsById(exam.getId())) {
			exam = repository.save(exam);
		}
		return exam;
	}

	public void delete(Long id) {
		repository.deleteById(id);		
	}

	public Exam findHealthcareInstitutionExam(Long healthcareInstitutionId, Long examId) {
		Exam exam = null;
		HealthcareInstitution institution = (institutionRepository.findById(healthcareInstitutionId)).get();
		if(institution.getCoins() > 0) {
			exam = find(examId);
			institution.setCoins(institution.getCoins()-1);
			institutionRepository.save(institution);
		}else {
			// TODO - criar exception para informar falta de moedas.
		}
		return exam;
	}
	
}
