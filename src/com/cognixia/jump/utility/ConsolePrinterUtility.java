package com.cognixia.jump.utility;

public class ConsolePrinterUtility {
	
	public static void prompt(String input) {
		System.out.println(ColorsUtility.ITALIC + input + ColorsUtility.RESET);
	}
	public static void promptOneLine(String input) {
		System.out.print(ColorsUtility.ITALIC + input + ColorsUtility.RESET);
	}
	
	public static void header(String input) {
		
	}

}
