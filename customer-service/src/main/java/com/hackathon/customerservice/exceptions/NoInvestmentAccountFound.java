package com.hackathon.customerservice.exceptions;

public class NoInvestmentAccountFound extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public NoInvestmentAccountFound(String message) {
		super(message);
	}

}
