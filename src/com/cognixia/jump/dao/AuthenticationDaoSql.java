package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.Customer;

public class AuthenticationDaoSql implements AuthenticationDao {
	
	private static Connection conn = ConnectionManager.getConnection();

	@Override
	public Optional<Customer> authenticate(String username, String password) {
		
		try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM customer WHERE email = ? AND password = ?")) {
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			Optional<Customer> customer = Optional.empty();
			
			while(rs.next()) {
				
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String custPassword = rs.getString("password");	
				
				customer = Optional.of(new Customer(id,null, firstName, lastName, email, custPassword));
			}
			
			return customer;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return Optional.empty();
	}
	
}
