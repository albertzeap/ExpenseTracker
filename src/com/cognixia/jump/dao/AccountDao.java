package com.cognixia.jump.dao;

import java.math.BigDecimal;
import java.util.Optional;

import com.cognixia.jump.model.Account;
import com.cognixia.jump.model.Customer;

public interface AccountDao {
	
	// GET Methods
	public Optional<Account> getUserAccount(Customer customer);
	
	// SET Methods
	public boolean setBudget(Account account);
	
	// POST Methods
	public boolean createAccount(int customerId, BigDecimal balance, BigDecimal monthlyBudget, BigDecimal yearlyBudget );

}
