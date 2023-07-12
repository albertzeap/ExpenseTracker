package com.cognixia.jump.model;

public class Customer {
	
	private int id;
	private int accountId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	
	
	public Customer() {
		super();
	}

	public Customer(int id, int accountId, String firstName, String lastName, String email, String password) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Customer [id=" + id + ", accountId=" + accountId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", password=" + password + "]";
	}
	
	
	
	
}
