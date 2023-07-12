package com.cognixia.jump.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.Account;
import com.cognixia.jump.model.Customer;

public class AccountDaoSql implements AccountDao {
	
	private static Connection conn = ConnectionManager.getConnection();

	@Override
	public Optional<Account> getUserAccount(Customer customer) {
		
		try(PreparedStatement ps = conn.prepareStatement("SELECT accounts.id, balance, monthly_budget, yearly_budget FROM accounts INNER JOIN customer ON accounts.id = ?;") ) {
			
			ps.setInt(1, customer.getAccountId());
			
			ResultSet rs = ps.executeQuery();
			Optional<Account> exists = Optional.empty();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				BigDecimal balance = rs.getBigDecimal("balance");
				BigDecimal monthlyBudget = rs.getBigDecimal("monthly_budget");
				BigDecimal yearlyBudget = rs.getBigDecimal("yearly_budget");
				
				exists = Optional.of(new Account(id, balance, monthlyBudget, yearlyBudget));
			}
			
			return exists;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return Optional.empty();
	}

}
