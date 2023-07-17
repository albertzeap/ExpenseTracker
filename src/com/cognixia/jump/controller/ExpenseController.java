package com.cognixia.jump.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cognixia.jump.dao.AccountDao;
import com.cognixia.jump.dao.AccountDaoSql;
import com.cognixia.jump.dao.CustomerDao;
import com.cognixia.jump.dao.CustomerDaoSql;
import com.cognixia.jump.dao.ExpenseDao;
import com.cognixia.jump.dao.ExpenseDaoSql;
import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Account;
import com.cognixia.jump.model.Customer;
import com.cognixia.jump.model.Expense;
import com.cognixia.jump.utility.ColorsUtility;
import com.cognixia.jump.utility.ConsolePrinterUtility;




public class ExpenseController {
	
	private static Customer activeUser = new Customer();
	private static Account activeAccount = new Account();
	private static Scanner scan = new Scanner(System.in);
	
	public static void run() {
		String choice = "";
		while(!choice.equals("3")) {
			choice = mainMenu();
			
			if(choice.equals("1")) {
				registerMenu();
			} else if(choice.equals("2")) {
				loginMenu();
				userMenu();
				
			} else if(choice.equals("3")) {
				System.out.println(ColorsUtility.ITALIC + ColorsUtility.GREEN + "Have a great day!" + ColorsUtility.RESET);
				return;
			}
		}
	}
		
	public static Account getActiveAccount() {
		return activeAccount;
	}

	public static void setActiveAccount(Account activeAccount) {
		ExpenseController.activeAccount = activeAccount;
	}

	public static Customer getActiveUser() {
		return activeUser;
	}

	public static void setActiveUser(Customer activeUser) {
		ExpenseController.activeUser = activeUser;
	}
	
	private static String mainMenu() {
		while (true) {
					
			System.out.println(ColorsUtility.CYAN_BOLD + "+---------------------+");
			System.out.println("+------ Welcome ------+");
			System.out.println("+---------------------+" + ColorsUtility.RESET);
			System.out.println("1. Create account");
			System.out.println("2. Login");
			System.out.println("3. Exit program\n");
//			System.out.print(ColorsUtility.ITALIC + "Choose an option (1-3): " + ColorsUtility.RESET);
			ConsolePrinterUtility.promptOneLine("Choose an option (1-3): ");
			String choice = scan.nextLine();
	
			while (!choice.matches("^[1-3]$")) {
				System.out.println(ColorsUtility.RED + "Not a valid choice.\n" + ColorsUtility.RESET);
				System.out.print(ColorsUtility.ITALIC + "Choose an option (1-3): "+ ColorsUtility.RESET);
				choice = scan.nextLine();
			}
			
			return choice;
		}
	}
	
	private static void registerMenu() {
		
		while(true) {

			System.out.println(ColorsUtility.CYAN_BOLD + "+---------------------+");
			System.out.println("+------ Register -----+");
			System.out.println("+---------------------+\n" + ColorsUtility.RESET);
			
			ConsolePrinterUtility.promptOneLine("Enter First Name: ");
			String firstName = scan.nextLine();
			
			ConsolePrinterUtility.promptOneLine("Enter Last Name: ");
			String lastName = scan.nextLine();
			
			ConsolePrinterUtility.promptOneLine("Enter an email: ");
			String username = scan.nextLine();
			
			Pattern pattern = Pattern.compile("[a-zA-Z]+[0-9]*[@]{1}[a-zA-Z]+[.]{1}[a-zA-Z]{3}");
			Matcher matcher = pattern.matcher(username);

			while (!matcher.find()) {
				System.out.println("Not a valid email.\n");
				ConsolePrinterUtility.promptOneLine("Enter email: ");
				username = scan.nextLine();
				matcher = pattern.matcher(username);
			}

			ConsolePrinterUtility.promptOneLine("Enter a password: ");
			String password = scan.nextLine();

			while (!password.matches("^.{1,}$")) {
				System.out.println("Not a valid password.\n");
				ConsolePrinterUtility.promptOneLine("Enter a password: ");
				password = scan.nextLine();
			}
			
			CustomerDao customerDao = new CustomerDaoSql();
			Optional<Customer> exists = customerDao.getCustomerByUsername(username);
			if (exists.isPresent()) {
				System.out.println(ColorsUtility.RED + "Username already taken.\n" + ColorsUtility.RESET);
			} else {
				customerDao.createCustomer(firstName, lastName, username, password);
				
				// Find the customer that was just created 
				Optional<Customer> customer = customerDao.getCustomerByUsername(username);
				
				// Create an account for the customer
				AccountDao accountDao = new AccountDaoSql();
				accountDao.createAccount(customer.get().getId(),new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"));
				break;
			}
			
		}
	}
	
	
	private static void loginMenu() {
		System.out.println(ColorsUtility.CYAN_BOLD +"+---------------------+");
		System.out.println("+------- Login -------+");
		System.out.println("+---------------------+" + ColorsUtility.RESET);

		while (true) {
			System.out.print("Username: ");
			String username = scan.nextLine();
			System.out.print("Password: ");
			String password = scan.nextLine();
			setActiveUser(AuthenticationController.authenticate(username, password));
			if(activeUser != null) {
				System.out.println(ColorsUtility.GREEN + "Login Successful!" + ColorsUtility.RESET);
				return;
			}

		}
	}
	
	// Menu to be displayed after user login
	private static void userMenu() {
		
		System.out.println(ColorsUtility.ITALIC + "\nWelcome to your Expense Tracker, " + activeUser.getFirstName() + "!\n" + ColorsUtility.RESET);
		
		int option = 0;
		
		while(true) {
			
			System.out.println(ColorsUtility.CYAN_BOLD +"+-------------------------+");
			System.out.println("+----- Your Dashboard ----+");
			System.out.println("+-------------------------+\n" + ColorsUtility.RESET);
			
			// Create an accountDao to connect to the database
			AccountDao accountDao = new AccountDaoSql();
			Optional<Account> userAccount = accountDao.getUserAccount(activeUser);
			if(userAccount.isEmpty()) {
				System.out.println(ColorsUtility.YELLOW + ColorsUtility.ITALIC + "No active accounts\n" + ColorsUtility.RESET);
				
				
				// Only print out account details if it exists to prevent nullPointerExceptions
			} else {
				setActiveAccount(userAccount.get());
				System.out.printf(ColorsUtility.YELLOW_UNDERLINED + "%-10s %-20s %-8s\n", "Balance", "Monthly Budget", "Yearly Budget" + ColorsUtility.RESET);
				System.out.printf("%-10s %-20s %-8s\n", activeAccount.getBalance(), activeAccount.getMonthlyBudget(), activeAccount.getYearlyBudget());			
				System.out.println();
				
//				System.out.printf(ColorsUtility.YELLOW_UNDERLINED + "%-10s %-20s %-8s\n", "Balance", "", "Yearly Budget" + ColorsUtility.RESET);
//				System.out.printf("%-10s %-20s %-8s\n", activeAccount.getBalance(), activeAccount.getMonthlyBudget(), activeAccount.getYearlyBudget());
				
				
			}
			
			System.out.println("1. Add an expense.");
			System.out.println("2. Remove a canceled expense.");
			System.out.println("3. Set a monthly budget");
			System.out.println("4. Set a yearly budget.");
			System.out.println("5. View upcoming expenses.");
			System.out.println("6. Exit.\n");
			System.out.print(ColorsUtility.ITALIC + "Choose an option (1-6): " + ColorsUtility.RESET);
			
			try {
				option = scan.nextInt();
				if (option < 1 || option > 6) {
					throw new Exception();
				}
			}
			catch (InputMismatchException e) {
				System.out.println(ColorsUtility.RED + "Enter a number.\n" + ColorsUtility.RESET);
				option = 0;
				scan.nextLine();
				continue;
			}
			catch (Exception e) {
				System.out.println(ColorsUtility.RED + "Not a valid option.\n" + ColorsUtility.RESET);
				option = 0;
				continue;
			}
			
			switch (option) {
			case 1:
				scan.nextLine();
				addExpense();
				break;
				
			case 2:
				removeExpense();
				break;
			case 3: 
				setMonthlyBudget();
				break;
			case 4: 
				setYearlyBudget();
				break;
			case 5:
				displayExpenses();
				break;
			case 6:
				System.out.println(ColorsUtility.ITALIC + ColorsUtility.GREEN + "Goodbye!\n"+ ColorsUtility.RESET );
				scan.nextLine();
				setActiveAccount(null);
				return;
			default:
				continue;
			}
		}
	}

	private static void addExpense() {
		System.out.println();
		
		try {
			
			ConsolePrinterUtility.prompt("What is the nature of the expense?");
			String nature = scan.nextLine();
			
			ConsolePrinterUtility.prompt("What is the date of the expense?");
			String sDate = scan.nextLine();
			if(!sDate.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
				throw new InputMismatchException();
			}
			// If date can be parsed then the date is a valid date
			LocalDate.parse(sDate);
			Date date = Date.valueOf(sDate);
			
			ConsolePrinterUtility.prompt("What is the cost of the expense?");
			BigDecimal price = scan.nextBigDecimal();
			
			ConsolePrinterUtility.prompt("Is the expense recurring?");
			boolean recurring = scan.nextBoolean();
			
			ExpenseDao expenseDao = new ExpenseDaoSql();
			boolean success = expenseDao.createExpense(new Expense(0, activeAccount.getId(), nature, date, price, recurring ), activeAccount);
			if(success) {
				System.out.println(ColorsUtility.GREEN + "Expense added successfully!" + ColorsUtility.RESET);
			} 
							
			
		} catch (InputMismatchException e) {
			System.out.println(ColorsUtility.RED + "Invalid Input:" + ColorsUtility.RESET);
			System.out.println(ColorsUtility.RED + "     For price please enter digits only." + ColorsUtility.RESET);
			System.out.println(ColorsUtility.RED + "     For date please follow format: yyyy-mm-dd" + ColorsUtility.RESET);
			System.out.println(ColorsUtility.RED + "     For recurring, please enter true or false" + ColorsUtility.RESET);
			scan.nextLine();
			
		} catch(DateTimeParseException e) {
			System.out.println(ColorsUtility.RED + "Invalid date. Please enter a valid date" + ColorsUtility.RESET);
			scan.nextLine();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			scan.nextLine();
		}
		
	}
	private static void removeExpense() {
		System.out.println(ColorsUtility.ITALIC + "Which expense would you like to delete?" + ColorsUtility.RESET);
		
		try {
			
			int expenseId = scan.nextInt();
			ExpenseDao expenseDao = new ExpenseDaoSql();
			
			boolean success = expenseDao.deleteExpense(expenseId, activeAccount);
			if(!success) {
				throw new ResourceNotFoundException("Expense ID");
			}
			
			System.out.println(ColorsUtility.GREEN + "Expense removed successfully!" + ColorsUtility.RESET);
			
		} catch(InputMismatchException e) {
			
			System.out.println(ColorsUtility.RED + "Invalid Input. Please enter the ID of your expense" + ColorsUtility.RESET);
			scan.nextLine();
			
		} catch(ResourceNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void setMonthlyBudget() {
		System.out.println(ColorsUtility.ITALIC + "What would you like your monthly budget to be?" + ColorsUtility.RESET);
		
		try {
			// Set both monthly and yearly budget based off the monthly budget value
			BigDecimal monthlyBudget = scan.nextBigDecimal();
			BigDecimal yearlyBudget = monthlyBudget.multiply(BigDecimal.valueOf(12.00));
			
			activeAccount.setMonthlyBudget(monthlyBudget);
			activeAccount.setYearlyBudget(yearlyBudget);
			
			AccountDao accountDao = new AccountDaoSql();
			boolean success = accountDao.setBudget(activeAccount);
			if(success) {
				System.out.println(ColorsUtility.GREEN + "Budget set successfully!" + ColorsUtility.RESET);
			} else {
				System.out.println(ColorsUtility.RED + "Budget could not be set." + ColorsUtility.RESET);
			}
			
			
		} catch(InputMismatchException e) { 
			System.out.println(ColorsUtility.RED + "Invalid Input. Please enter digits" + ColorsUtility.RESET);
			scan.nextLine();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private static void setYearlyBudget() {
		System.out.println(ColorsUtility.ITALIC + "What would you like your yearly budget to be?" + ColorsUtility.RESET);
		
		try {
			// Set both monthly and yearly budget based off the monthly budget value
			BigDecimal yearlyBudget = scan.nextBigDecimal();
			BigDecimal monthlyBudget = yearlyBudget.divide(BigDecimal.valueOf(12.00), RoundingMode.HALF_UP);
			
			activeAccount.setMonthlyBudget(monthlyBudget);
			activeAccount.setYearlyBudget(yearlyBudget);
			
			AccountDao accountDao = new AccountDaoSql();
			boolean success = accountDao.setBudget(activeAccount);
			if(success) {
				System.out.println(ColorsUtility.GREEN + "Budget set successfully!" + ColorsUtility.RESET);
			} else {
				System.out.println(ColorsUtility.RED + "Budget could not be set." + ColorsUtility.RESET);
			}
			
			
		} catch(InputMismatchException e) { 
			System.out.println(ColorsUtility.RED + "Invalid Input. Please enter digits" + ColorsUtility.RESET);
			scan.nextLine();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	private static void displayExpenses() {
		System.out.println(ColorsUtility.CYAN_BOLD +"+-------------------------+");
		System.out.println("+----- Your Expenses ----+");
		System.out.println("+-------------------------+\n" + ColorsUtility.RESET);
		
		try {
			
			ExpenseDao expenseDao = new ExpenseDaoSql();
			List<Expense> expenses = expenseDao.viewExpenses(activeAccount);
			if(expenses.isEmpty()) {
				System.out.println(ColorsUtility.YELLOW + ColorsUtility.ITALIC + "No upcoming expenses\n" + ColorsUtility.RESET);
			} else {
				
				System.out.printf(ColorsUtility.YELLOW_UNDERLINED + "%-10s %-15s %-15s %-15s %-15s %-8s\n", "ID", "Account ID", "Nature", "Date", "Price", "Recurring" + ColorsUtility.RESET);
				for(Expense expense : expenses) {
					System.out.printf(ColorsUtility.RESET + "%-10d %-15d %-15s %-15s %-15s %-8s\n", 
							expense.getId(), 
							expense.getAccountsId(), 
							expense.getNature(),
							expense.getDate(),
							expense.getPrice(), 
							expense.isRecurring());
				}
				System.out.println(ColorsUtility.RESET);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}




}
