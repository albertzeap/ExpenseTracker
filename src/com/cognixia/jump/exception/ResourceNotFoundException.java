package com.cognixia.jump.exception;

import com.cognixia.jump.utility.ColorsUtility;

public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String resource) { 
		super(ColorsUtility.RED + resource +" could not be found or does not exist." + ColorsUtility.RESET);
	}

}
