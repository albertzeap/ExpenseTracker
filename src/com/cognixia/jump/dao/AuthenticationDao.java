package com.cognixia.jump.dao;

import java.util.Optional;

import com.cognixia.jump.model.Customer;

public interface AuthenticationDao {

	// Authenticate the user
	public Optional<Customer> authenticate(String username, String password);
	
}
