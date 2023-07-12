package com.cognixia.jump.model;

import java.time.LocalDateTime;

public class Expense {

	private int id;
	private int accountsId;
	private String nature;
	private LocalDateTime date;
	private boolean recurring;
	
	public Expense() {
	
	}

	public Expense(int id, int accountsId, String nature, LocalDateTime date, boolean recurring) {
		super();
		this.id = id;
		this.accountsId = accountsId;
		this.nature = nature;
		this.date = date;
		this.recurring = recurring;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountsId() {
		return accountsId;
	}

	public void setAccountsId(int accountsId) {
		this.accountsId = accountsId;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public boolean isRecurring() {
		return recurring;
	}

	public void setRecurring(boolean recurring) {
		this.recurring = recurring;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", accountsId=" + accountsId + ", nature=" + nature + ", date=" + date
				+ ", recurring=" + recurring + "]";
	}
	
	
	
	
	
}
