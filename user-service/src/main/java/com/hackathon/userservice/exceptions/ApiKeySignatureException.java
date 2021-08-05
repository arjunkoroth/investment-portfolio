package com.hackathon.userservice.exceptions;

public class ApiKeySignatureException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ApiKeySignatureException(String message) {
		super(message);
	}

	public ApiKeySignatureException(String message, Throwable cause) {
		super(message, cause);
	}
}
