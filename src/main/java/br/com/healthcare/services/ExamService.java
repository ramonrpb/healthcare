package br.com.healthcare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.healthcare.repositories.ExamRepository;

@Service
public class ExamService {

	@Autowired
	private ExamRepository repository;
	
}
