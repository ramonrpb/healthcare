package br.com.healthcare.repositories;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import br.com.healthcare.domain.Exam;

@Repository
public interface ExamRepository extends JpaRepositoryImplementation<Exam, Long> {

}
