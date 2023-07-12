package com.cognixia.jump.model;

import java.math.BigDecimal;
import java.sql.Date;


public class Expense {

	private int id;
	private int accountsId;
	private String nature;
	private Date date;
	private BigDecimal price;
	private boolean recurring;
	
	public Expense() {
	
	}

	public Expense(int id, int accountsId, String nature, Date date, BigDecimal price, boolean recurring) {
		super();
		this.id = id;
		this.accountsId = accountsId;
		this.nature = nature;
		this.date = date;
		this.price = price;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public boolean isRecurring() {
		return recurring;
	}

	public void setRecurring(boolean recurring) {
		this.recurring = recurring;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", accountsId=" + accountsId + ", nature=" + nature + ", date=" + date + ", price="
				+ price + ", recurring=" + recurring + "]";
	}

	
	
	
	
}
