package com.cognixia.jump.dao;

import com.cognixia.jump.model.Account;
import com.cognixia.jump.model.Customer;
import com.cognixia.jump.model.Expense;

public interface ExpenseDao {
	
	public boolean createExpense(Expense expense, Account account);
	
	public boolean deleteExpense(int expenseId, Account account);
}
