package com.capgemini.bank.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
private static Connection connection = null;

public static Connection getConnection() {
	try {
		
		//this is not required since jdk 1.8
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		
		//one more way to register 
		//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		connection = DriverManager.getConnection(url, "cg", "cg");
	}catch (ClassNotFoundException e) {
		
		e.printStackTrace();
	} 
	catch (SQLException e) {
		
	} 
	return connection;
	
}
public static void main(String[] args) {
	System.out.println(DBConnection.getConnection());
}
}
