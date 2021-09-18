package org.simplilearn.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

	private Connection connection;
	
	public DbConnection() throws Exception{
		
		Class.forName("com.mysql.jdbc.Driver");
		
		String dbURL="jdbc:mysql://localhost:3306/ecommerce";
		String dbUser="root";
		String dbPassword="admin";
		
		this.connection = DriverManager.getConnection(dbURL,dbUser,dbPassword);
	}
	
	public void closeConnection() throws Exception{
		if(this.connection !=null) {
			this.connection.close();
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
}
