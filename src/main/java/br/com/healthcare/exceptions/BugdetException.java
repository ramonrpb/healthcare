package br.com.healthcare.exceptions;

public class BugdetException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BugdetException(String msg) {
		super(msg);
	}
	
	public BugdetException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
