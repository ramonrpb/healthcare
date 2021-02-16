package br.com.healthcare.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.healthcare.services.ExamService;

@RestController
@RequestMapping(value = "/exams")
public class ExamController {

	@Autowired
	private ExamService service;
	
}
