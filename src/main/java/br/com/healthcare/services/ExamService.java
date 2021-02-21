package br.com.healthcare.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import br.com.healthcare.domain.Exam;
import br.com.healthcare.domain.HealthcareInstitution;
import br.com.healthcare.exceptions.BugdetException;
import br.com.healthcare.exceptions.ExamException;
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

	public Exam findByIdAndHealthcareInstitution(Long id, HealthcareInstitution healthcareInstitution) {
		Optional<Exam> obj = repository.findByIdAndHealthcareInstitution(id, healthcareInstitution);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Exame não encontrado! Id: " + id + " para a intituição: "+ healthcareInstitution.getId() +", Tipo: " + Exam.class.getName()));
	}
	
	public @Valid Exam insert(@Valid Exam exam) {
		
		try {
			HealthcareInstitution institution = (institutionRepository.findById(exam.getHealthcareInstitution().getId())).get();
			if(institution.getCoins() > 0) {
				exam.setRead(false);
				exam = repository.save(exam);
				institution.setCoins(institution.getCoins()-1);
				institutionRepository.save(institution);
			}else {
				throw new BugdetException("Sem saldo suficiente para cadastro de novos exames!");
			}
			
			return exam;
		} catch (NoSuchElementException e) {
			throw new ExamException("Para cadastrar um exame é necessário informar uma instituição existente.");
		}
	}

	public @Valid Exam update(@Valid Exam exam) {
		if(repository.existsById(exam.getId())) {
			Exam examOld = find(exam.getId());
			exam.setRead(examOld.isRead());
			exam = repository.save(exam);
		}
		return exam;
	}

	public void delete(Long id) {
		repository.deleteById(id);		
	}

	public Exam findHealthcareInstitutionExam(Long healthcareInstitutionId, Long examId) {
		Exam exam = null;
		
		Optional<HealthcareInstitution> obj = ((CrudRepository<HealthcareInstitution, Long>) institutionRepository).findById(healthcareInstitutionId);
		obj.orElseThrow(() -> new ObjectNotFoundException(
				"Instituição não encontrada! Id: " + healthcareInstitutionId + ", Tipo: " + HealthcareInstitution.class.getName()));
		
		HealthcareInstitution institution = (institutionRepository.findById(healthcareInstitutionId)).get();
		
		if(institution.getCoins() > 0) {
			exam = findByIdAndHealthcareInstitution(examId, institution);
			if(!exam.isRead()) {
				institution.setCoins(institution.getCoins()-1);
			}
			exam.setRead(true);
			repository.save(exam);
			institutionRepository.save(institution);
		}else {
			throw new BugdetException("Sem saldo suficiente para consulta a novos exames!");
		}
		return exam;
	}
	
}
