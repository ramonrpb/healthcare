package br.com.healthcare.exceptions;

public class InvalidCnpjException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidCnpjException(String msg) {
		super(msg);
	}
	
	public InvalidCnpjException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
