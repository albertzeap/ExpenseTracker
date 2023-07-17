package com.cognixia.jump.model;

import java.math.BigDecimal;

public class Account {
	
	private int id;
	private int customerId;
	private BigDecimal balance;
	private BigDecimal monthlyBudget;
	private BigDecimal yearlyBudget;
	
	
	public Account() {
		
	}

	public Account(int id, int customerId, BigDecimal balance, BigDecimal monthlyBudget, BigDecimal yearlyBudget) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.balance = balance;
		this.monthlyBudget = monthlyBudget;
		this.yearlyBudget = yearlyBudget;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getMonthlyBudget() {
		return monthlyBudget;
	}

	public void setMonthlyBudget(BigDecimal monthlyBudget) {
		this.monthlyBudget = monthlyBudget;
	}

	public BigDecimal getYearlyBudget() {
		return yearlyBudget;
	}

	public void setYearlyBudget(BigDecimal yearlyBudget) {
		this.yearlyBudget = yearlyBudget;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", customerId=" + customerId + ", balance=" + balance + ", monthlyBudget="
				+ monthlyBudget + ", yearlyBudget=" + yearlyBudget + "]";
	}

	
	
	
	
	
}
