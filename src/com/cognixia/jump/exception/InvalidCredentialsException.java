package com.cognixia.jump.exception;

import com.cognixia.jump.utility.ColorsUtility;

public class InvalidCredentialsException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public InvalidCredentialsException() {
		super(ColorsUtility.RED + "Incorrect username or password" + ColorsUtility.RESET);
	}

}
