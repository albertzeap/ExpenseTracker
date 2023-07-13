package com.cognixia.jump.dao;

import java.util.List;

import com.cognixia.jump.model.Account;
import com.cognixia.jump.model.Expense;

public interface ExpenseDao {
	
	public boolean createExpense(Expense expense, Account account);
	
	public boolean deleteExpense(int expenseId, Account account);
	
	public List<Expense> viewExpenses(Account account);
}
