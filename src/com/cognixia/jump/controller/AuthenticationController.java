package com.cognixia.jump.controller;

import java.util.Optional;

import com.cognixia.jump.dao.AuthenticationDao;
import com.cognixia.jump.dao.AuthenticationDaoSql;
import com.cognixia.jump.exception.InvalidCredentialsException;
import com.cognixia.jump.model.Customer;

public class AuthenticationController {
	
	public static Customer authenticate(String username, String password) {
		
		AuthenticationDao auth = new AuthenticationDaoSql();
		Optional<Customer> customer = auth.authenticate(username, password);			
		try {	
			
			if(customer.isEmpty()) {
				throw new InvalidCredentialsException();
			}
			
		} catch (InvalidCredentialsException e) {
			System.out.println(e.getMessage());
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return customer.get();
	}
}
