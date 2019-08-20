
/**
 * 
 */
package com.itp.services;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.itp.model.Employee;
import com.itp.util.CommonConstants;
import com.itp.util.CommonUtilities;
import com.itp.util.DBConnection;
import com.itp.util.QueryUtilities;



/**
 * @author Yuvin Nimnaka
 *
 */
public class EmployeeServiceImpl implements IEmployeeServices{

	public static final Logger log = Logger.getLogger(EmployeeServiceImpl.class.getName());

	private static Connection connection;
	
	private static Statement statement;
	
	private static PreparedStatement preparedstatement;

	@Override
	public void addEmployee(Employee employee) {
		
		String employeeID = CommonUtilities.generateEmployeeID(getEmployeeIDs());
		
		try {
			
			connection =DBConnection.getDBConnection();
			
			preparedstatement = connection.prepareStatement(QueryUtilities.queryByID(CommonConstants.QUERY_ID_INSERT_EMPLOYEE));
			connection.setAutoCommit(false);
			
			//get employee ids
			employee.setEmployeeID(employeeID);
			
		
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	@Override
	public Employee getEmployeeByID(String empoyeeID) {
		
		return null;
	}

	
	@Override
	public ArrayList<Employee> getEmployees() {
		
		return null;
	}


	@Override
	public Employee updateEmployee(String employeeID, Employee employee) {
		
		return null;
	}

	@Override
	public void removeEmployee(String employeeID) {
		
		
	}


	/* (non-Javadoc)
	 * @see com.itp.services.IEmployeeServices#getEmployeeIDs()
	 */

	private ArrayList<String> getEmployeeIDs() {
		
		ArrayList<String> idList = new ArrayList<String>();
		try {
			
			connection = DBConnection.getDBConnection();
			preparedstatement = connection.prepareStatement(QueryUtilities.queryByID(CommonConstants.QUERY_ID_GET_EMPLOYEE_IDS));
			ResultSet resultSet = preparedstatement.executeQuery();
			
			while(resultSet.next()) {
				idList.add(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
			}
			
		} catch (SQLException | SAXException | IOException | ParserConfigurationException
				| ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedstatement != null) {
					preparedstatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return idList;
	}

}
