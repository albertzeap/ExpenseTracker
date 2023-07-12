package com.cognixia.jump.dao;

import java.util.Optional;

import com.cognixia.jump.model.Account;
import com.cognixia.jump.model.Customer;

public interface AccountDao {
	
	// GET Methods
	public Optional<Account> getUserAccount(Customer customer);

}
