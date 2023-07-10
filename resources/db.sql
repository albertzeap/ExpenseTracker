-- Creation of the database 
CREATE DATABASE IF NOT EXISTS expense_db;
use expense_db;

-- Creation of the tables
CREATE TABLE IF NOT EXISTS accounts (
	id INT PRIMARY KEY AUTO_INCREMENT,
    balance DECIMAL(10,2) DEFAULT 0,
    monthly_budget DECIMAL(10,2),
    yearly_budget DECIMAL (10,2)
);

CREATE TABLE IF NOT EXISTS customer (
	id 	INT 
		PRIMARY KEY 
		AUTO_INCREMENT,
	accounts_id INT,
	first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    email VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(30) NOT NULL,
    dob DATE,
    
    FOREIGN KEY(accounts_id) REFERENCES accounts(id)
);

CREATE TABLE IF NOT EXISTS expense(
	id INT PRIMARY KEY AUTO_INCREMENT,
    accounts_id INT,
    nature VARCHAR(255) DEFAULT 'Other',
    expense_date DATE,
    recurring BOOLEAN DEFAULT 0,
    
    FOREIGN KEY(accounts_id) REFERENCES accounts(id)
);





