package com.cognixia.jump.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

	// by default, the connection will be null
	private static Connection connection;
	
	
	//establish the connection if it's not already connected
	private static void makeConnection() {
		
		// Properties will be used to access our properties file and read its values
		Properties props = new Properties();
		
		try {
			// load in the data from the file using a file stream
			props.load( new FileInputStream("resources/config.properties") );
			
			// save the values as variables from the properties file
			String url = props.getProperty("url");
			String username = props.getProperty("username");
			String password = props.getProperty("password");
			
			//establish the connection
			Class.forName("com.mysql.cj.jdbc.Driver"); // load in the driver
			connection = DriverManager.getConnection(url, username, password);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// returns the single connection objected stored in the class
	public static Connection getConnection() {
		
		// not make the connection again if we are already connected
		if (connection == null) {
			makeConnection();
		}
		
		return connection;
	}
	
	public static void main(String[] args) {
		
		System.out.println("welcome to our Program!");
		
		System.out.println("Establishing db conenction...");
		
		
			Connection connection = ConnectionManager.getConnection();
			System.out.println("Connection made!");
	}

}
