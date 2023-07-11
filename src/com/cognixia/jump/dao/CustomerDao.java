package com.cognixia.jump.dao;

import java.util.Optional;

import com.cognixia.jump.model.Customer;

public interface CustomerDao {
	
	// GET Methods
	public Optional<Customer> getCustomerByUsername(String username);
	
	// POST Methods
	public boolean createCustomer(String firstName, String lastName, String email, String password);
	
	
}
