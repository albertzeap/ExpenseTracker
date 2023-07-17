-- Creation of the database 
CREATE DATABASE IF NOT EXISTS expense_db;
use expense_db;

-- Creation of the tables
CREATE TABLE IF NOT EXISTS customer (
	id 	INT 
		PRIMARY KEY 
		AUTO_INCREMENT,
	first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    email VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(30) NOT NULL
    
);

CREATE TABLE IF NOT EXISTS accounts (
	id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    balance DECIMAL(10,2) DEFAULT 0,
    monthly_budget DECIMAL(10,2),
    yearly_budget DECIMAL (10,2),
    
	FOREIGN KEY(customer_id) REFERENCES customer(id)
);


CREATE TABLE IF NOT EXISTS expense(
	id INT PRIMARY KEY AUTO_INCREMENT,
    accounts_id INT,
    nature VARCHAR(255) DEFAULT 'Other',
    expense_date DATE,
    price DECIMAL(10,2),
    recurring BOOLEAN DEFAULT 0,
    
    FOREIGN KEY(accounts_id) REFERENCES accounts(id)
);

-- Seed the database
INSERT INTO accounts (balance, monthly_budget, yearly_budget)
VALUES (1000.00, 500.00, 6000.00);

INSERT INTO customer(first_name, last_name, email, password)
VALUES ("Albert", "Paez", "albertzeap@gmail.com", "password");
       





