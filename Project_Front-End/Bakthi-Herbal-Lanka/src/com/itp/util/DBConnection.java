package com.itp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnection extends CommonUtilities{
	
	private static Connection connection;
	
	private DBConnection(){
		
		
	}
	
//	public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
		
//		if(connection==null || connection.isClosed()) {
//			Class.forName(properties.getProperty(CommanConstants.DRIVER_NAME));
//			connection = DriverManager.getConnection(properties.getProperty(CommanConstants.URL),
//					properties.getProperty(CommanConstants.USERNAME), properties.getProperty(CommanConstants.PASSWORD));
//			System.out.println("Connected");
//		}
//		
//		return connection;
//	}
//	}
}
