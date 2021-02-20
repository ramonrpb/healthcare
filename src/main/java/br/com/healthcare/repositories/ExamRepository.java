package br.com.healthcare.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import br.com.healthcare.domain.Exam;
import br.com.healthcare.domain.HealthcareInstitution;

@Repository
public interface ExamRepository extends JpaRepositoryImplementation<Exam, Long> {

	Optional<Exam> findByIdAndHealthcareInstitution(Long examId, HealthcareInstitution healthcareInstitutionId);
}
