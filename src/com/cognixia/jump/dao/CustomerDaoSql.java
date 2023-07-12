package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.Customer;

public class CustomerDaoSql implements CustomerDao {
	
	private static Connection conn = ConnectionManager.getConnection();

	@Override
	public boolean createCustomer(String firstName, String lastName, String email, String password) {
		
		try(PreparedStatement ps = conn.prepareStatement("INSERT INTO customer VALUES (null, null, ?, ?, ?, ?)")) {
			
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, email);
			ps.setString(4, password);
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public Optional<Customer> getCustomerByUsername(String username) {
		
		try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM customer WHERE email = ?")) {
			
			ps.setString(1, username);
			
			
			ResultSet rs = ps.executeQuery();
			Optional<Customer> exists = Optional.empty();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				int accountId = rs.getInt("accounts_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String custPassword = rs.getString("password");	
				
				exists = Optional.of(new Customer(id,accountId, firstName, lastName, email, custPassword));
			}
			
			return exists;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return Optional.empty();
	}

}
