package br.com.healthcare.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.healthcare.utils.Util;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp, Util.formatDateToString(new Date()));
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

}
