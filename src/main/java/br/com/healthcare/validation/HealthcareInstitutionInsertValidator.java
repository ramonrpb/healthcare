package br.com.healthcare.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.healthcare.domain.HealthcareInstitution;
import br.com.healthcare.exceptions.FieldMessage;
import br.com.healthcare.repositories.HealthcareInstitutionRepository;
import br.com.healthcare.utils.Util;

public class HealthcareInstitutionInsertValidator implements ConstraintValidator<HealthcareInstitutionInsert, HealthcareInstitution> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HealthcareInstitutionRepository repository;
	
	@Override
	public void initialize(HealthcareInstitutionInsert ann) {
	}

	@Override
	public boolean isValid(HealthcareInstitution obj, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(null != request) {
			@SuppressWarnings("unchecked")
			Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
			if(map.isEmpty() || map.get("id") == null) {
				if(!Util.isValidCNPJ(obj.getCnpj().replaceAll("[^0-9]", ""))) {
					list.add(new FieldMessage("cnpj", "CNPJ inválido"));
				}
				if(null != repository) {
					if(null != obj.getCnpj() && !obj.getCnpj().isEmpty()) {
						HealthcareInstitution instituition = repository.findByCnpj(obj.getCnpj());
						if(null != instituition) {
							list.add(new FieldMessage("cnpj", "CNPJ já existente"));
						}
					}
				}	
				for (FieldMessage e : list) {
					context.disableDefaultConstraintViolation();
					context.buildConstraintViolationWithTemplate(e.getFieldMessage()).addPropertyNode(e.getFieldName())
							.addConstraintViolation();
				}
			}
		}
		return list.isEmpty();
	}
}
