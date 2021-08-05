package com.hackathon.customerservice.exceptions;

public class NoInvestmentAccountFoundException extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public NoInvestmentAccountFoundException(String message) {
		super(message);
	}

}
