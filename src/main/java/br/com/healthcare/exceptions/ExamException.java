package br.com.healthcare.exceptions;

public class ExamException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExamException(String msg) {
		super(msg);
	}
	
	public ExamException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
