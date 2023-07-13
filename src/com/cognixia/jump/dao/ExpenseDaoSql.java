package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.Account;
import com.cognixia.jump.model.Expense;

public class ExpenseDaoSql implements ExpenseDao {
	
	private static Connection conn = ConnectionManager.getConnection();

	@Override
	public boolean createExpense(Expense expense, Account account) {
		
		try(PreparedStatement ps = conn.prepareStatement("INSERT INTO expense VALUES (null, ?, ?, ?, ?, ?) ")) {
			
			ps.setInt(1, account.getId());
			ps.setString(2, expense.getNature());
			ps.setDate(3, expense.getDate());
			ps.setBigDecimal(4, expense.getPrice());
			ps.setBoolean(5, expense.isRecurring());
			
			int success = ps.executeUpdate();
			if(success > 0) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return false;
	}

	@Override
	public boolean deleteExpense(int expenseId, Account account) {


		try(PreparedStatement ps = conn.prepareStatement("DELETE FROM expense WHERE expense.id = ? AND expense.accounts_id = ?  ")) {
			
			ps.setInt(1, expenseId);
			ps.setInt(2, account.getId());
			
			int success = ps.executeUpdate();
			if(success > 0) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return false;
	}

}
