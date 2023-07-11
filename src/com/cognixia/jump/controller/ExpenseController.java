package com.cognixia.jump.controller;

import java.util.Scanner;

import com.cognixia.jump.model.Customer;
import com.cognixia.jump.utility.ColorsUtility;



public class ExpenseController {
	
	private static Customer activeUser = new Customer();
	private static Scanner scan = new Scanner(System.in);
	
	public static void run() {
		String choice = mainMenu();
		
		if(choice.equals("1")) {
			
		} else if(choice.equals("2")) {
			loginMenu();
		} else if(choice.equals("3")) {
			System.out.println(ColorsUtility.ITALIC + ColorsUtility.GREEN + "Have a great day!" + ColorsUtility.RESET);
			return;
		}
			
			
	}
		
		
		

	public static Customer getActiveUser() {
		return activeUser;
	}

	public static void setActiveUser(Customer activeUser) {
		ExpenseController.activeUser = activeUser;
	}
	
	public static String mainMenu() {
		while (true) {
					
			System.out.println(ColorsUtility.CYAN_BOLD + "+---------------------+");
			System.out.println("+------ Welcome ------+");
			System.out.println("+---------------------+" + ColorsUtility.RESET);
			System.out.println("1. Create account");
			System.out.println("2. Login");
			System.out.println("3. Exit program\n");
			System.out.print(ColorsUtility.ITALIC + "Choose an option (1-3): " + ColorsUtility.RESET);
			String choice = scan.nextLine();
	
			while (!choice.matches("^[1-3]$")) {
				System.out.println(ColorsUtility.RED + "Not a valid choice.\n" + ColorsUtility.RESET);
				System.out.print(ColorsUtility.ITALIC + "Choose an option (1-3): "+ ColorsUtility.RESET);
				choice = scan.nextLine();
			}
			
			return choice;
		}
	}
	
	public static void loginMenu() {
		System.out.println(ColorsUtility.CYAN_BOLD +"+---------------------+");
		System.out.println("+------- Login -------+");
		System.out.println("+---------------------+" + ColorsUtility.RESET);

		while (true) {
			System.out.print("Username: ");
			String username = scan.nextLine();
			System.out.print("Password: ");
			String password = scan.nextLine();
			setActiveUser(AuthenticationController.authenticate(username, password));

		}
	}

}
