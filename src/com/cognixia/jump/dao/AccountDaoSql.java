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
		
		try(PreparedStatement ps = conn.prepareStatement("select accounts.id, accounts.customer_id, balance, monthly_budget, yearly_budget from accounts join customer ON accounts.customer_id = ?;") ) {
			
			ps.setInt(1, customer.getId());
			
			ResultSet rs = ps.executeQuery();
			Optional<Account> exists = Optional.empty();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				int customerId = rs.getInt("customer_id");
				BigDecimal balance = rs.getBigDecimal("balance");
				BigDecimal monthlyBudget = rs.getBigDecimal("monthly_budget");
				BigDecimal yearlyBudget = rs.getBigDecimal("yearly_budget");
				
				exists = Optional.of(new Account(id, customerId,  balance, monthlyBudget, yearlyBudget));
			}
			
			return exists;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return Optional.empty();
	}

	@Override
	public boolean setBudget(Account account) {
		
		try(PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET monthly_budget = ?, yearly_budget = ? WHERE id = ?")) {
			
			ps.setBigDecimal(1, account.getMonthlyBudget());
			ps.setBigDecimal(2, account.getYearlyBudget());
			ps.setInt(3, account.getId());
			
			int count = ps.executeUpdate();
			if(count > 0) {
				return true;
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return false;
	}

	@Override
	public boolean createAccount(int customerId, BigDecimal balance, BigDecimal monthlyBudget, BigDecimal yearlyBudget) {
		
		try(PreparedStatement ps = conn.prepareStatement("INSERT INTO accounts VALUES (null, ?, ?, ?, ?) ")) {
			
			ps.setInt(1, customerId);
			ps.setBigDecimal(2, balance);
			ps.setBigDecimal(3, monthlyBudget);
			ps.setBigDecimal(4, yearlyBudget);
			
			int count = ps.executeUpdate();
			if(count > 0) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
