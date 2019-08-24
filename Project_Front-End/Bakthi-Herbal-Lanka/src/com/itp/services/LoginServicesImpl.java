
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
import com.itp.model.Login;
import com.itp.util.CommonConstants;
import com.itp.util.CommonUtilities;
import com.itp.util.DBConnection;
import com.itp.util.QueryUtilities;

/**
 * @author Yuvin Nimnaka
 *
 */
public class LoginServicesImpl implements ILoginServices {

	public static final Logger log = Logger.getLogger(LoginServicesImpl.class.getName());

	private static Connection connection;

	private static Statement statement;

	private static PreparedStatement preparedstatement;
	
	@Override
	public void addLogin(Login login) {
		
		String loginID = CommonUtilities.generateLoginID(getLoginIDs());
		
		try {

			connection = DBConnection.getDBConnection();

			preparedstatement = connection
					.prepareStatement(QueryUtilities.queryByID(CommonConstants.QUERY_ID_INSERT_LOGIN));
			connection.setAutoCommit(false);

			// get login ids
			login.setLoginID(loginID);
			preparedstatement.setString(CommonConstants.COLUMN_INDEX_ONE, login.getLoginID());
			preparedstatement.setString(CommonConstants.COLUMN_INDEX_TWO, login.getName());
			preparedstatement.setString(CommonConstants.COLUMN_INDEX_THREE, login.getDesignation());
			preparedstatement.setString(CommonConstants.COLUMN_INDEX_FOUR, login.getUsername());
			preparedstatement.setString(CommonConstants.COLUMN_INDEX_FIVE, login.getPassword());
			preparedstatement.execute();
			connection.commit();

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of transaction
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
		
	}

	
	@Override
	public Employee getLoginByID(String empoyeeID) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public ArrayList<Login> getLogin() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Employee updateLogin(String loginID, Login login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeLogin(String loginID) {
	
		if (loginID != null && !loginID.isEmpty()) {

			try {
				connection = DBConnection.getDBConnection();
				preparedstatement = connection
						.prepareStatement(QueryUtilities.queryByID(CommonConstants.QUERY_ID_REMOVE_LOGIN));
				preparedstatement.setString(CommonConstants.COLUMN_INDEX_ONE, loginID);
				preparedstatement.executeUpdate();

			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				log.log(Level.SEVERE, e.getMessage());

			} finally {
				/*
				 * Close prepared statement and database connectivity at the end of transaction
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
		}
		
	}
	
	private ArrayList<String> getLoginIDs(){
		
		ArrayList<String> idList = new ArrayList<String>();
		try {

			connection = DBConnection.getDBConnection();
			preparedstatement = connection
					.prepareStatement(QueryUtilities.queryByID(CommonConstants.QUERY_ID_GET_LOGIN_IDS));
			ResultSet resultSet = preparedstatement.executeQuery();

			while (resultSet.next()) {
				idList.add(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
			}

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of transaction
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
