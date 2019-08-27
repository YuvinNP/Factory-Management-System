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

import com.itp.model.Supplier;
import com.itp.model.rawMaterial;
import com.itp.util.CommonConstants;
import com.itp.util.CommonUtilities;
import com.itp.util.DBConnection;
import com.itp.util.QueryUtilities;

public class RawMaterialServiceImpl implements IRawMaterialServices{

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(RawMaterialServiceImpl.class.getName());

	private static Connection connection;

	private static Statement statement;
	
	private static PreparedStatement preparedstatement;

	/* ADD Raw Materials BEGIN*/
	
	public void addRawMaterial(rawMaterial raw_material) {
		
	String rawMaterialID = CommonUtilities.generateRawMaterialID(getRawMaterialIDs());

	try {

		connection = DBConnection.getDBConnection();

		preparedstatement = connection
				.prepareStatement(QueryUtilities.queryByID(CommonConstants.QUERY_ID_INSERT_RawMaterial));
		connection.setAutoCommit(false);

		raw_material.setRawMaterialID(rawMaterialID);
		preparedstatement.setString(CommonConstants.COLUMN_INDEX_ONE, raw_material.getRawMaterialID());
		preparedstatement.setString(CommonConstants.COLUMN_INDEX_TWO, raw_material.getRawMaterialName());
		preparedstatement.setString(CommonConstants.COLUMN_INDEX_THREE, raw_material.getSupplierID());
		preparedstatement.setString(CommonConstants.COLUMN_INDEX_FOUR, raw_material. getStoreID());
		preparedstatement.setDouble(CommonConstants.COLUMN_INDEX_FIVE, raw_material.getUnitPrice());
		preparedstatement.execute();
		connection.commit();
		

	} catch (SQLException | SAXException | ParserConfigurationException | ClassNotFoundException | IOException e) {
		System.out.println(e);
		System.out.println("Error");

	} finally {
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
}/* ADD RAW MATERIALS END*/

public ArrayList<String> getRawMaterialIDs(){
	
	ArrayList<String> arrayList = new ArrayList<String>();
	/*
	 * List of raw- material IDs will be retrieved from factory.xml
	 */
	try {
		connection = DBConnection.getDBConnection();
		preparedstatement = connection
				.prepareStatement(QueryUtilities.queryByID(CommonConstants.QUERY_ID_GET_RawMaterial_IDS));
		ResultSet resultSet = preparedstatement.executeQuery();
		while (resultSet.next()) {
			arrayList.add(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
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
	return arrayList;
}

/**
 * Raw Material details can be retrieved based on the provided raw material ID
 * 
 * @param rawMaterialID
 *            - raw Material details are filtered based on the ID
 * 
 * @see #actionOnrawMaterial()
 */
@Override
public rawMaterial getRawMaterialByID(String rawMaterialID) {
	
	return actionOnrawMaterial(rawMaterialID).get(0);
}

/**
 * Get all list of rawMaterial
 * 
 * @return ArrayList<rawMaterial> 
 * 						- Array of rawMaterial list will be return
 * 
 * @see #actionOnrawMaterial()
 */
@Override
public ArrayList<rawMaterial> getRawMaterilas() {
	
	return actionOnrawMaterial(null);
}

private ArrayList<rawMaterial> actionOnrawMaterial(String rawMaterialID){ 

	ArrayList<rawMaterial> rawMaterialList = new ArrayList<rawMaterial>();
	
	try {
		connection = DBConnection.getDBConnection();
		/*
		 * Before fetching rawMaterial it checks whether raw Material ID is
		 * available
		 */
		if(rawMaterialID != null && !rawMaterialID.isEmpty()) {
			/*
			 * Get raw material by ID query will be retrieved from
			 * Factory.xml
			 */
			preparedstatement = connection.prepareStatement(QueryUtilities.queryByID(CommonConstants.QUERY_ID_GET_RawMaterial_ID));
			preparedstatement.setString(CommonConstants.COLUMN_INDEX_ONE, rawMaterialID);	
		}
		/*
		 * If raw material ID is not provided for get raw material option it display
		 * all raw material
		 */
		else {
			preparedstatement = connection.prepareStatement(QueryUtilities.queryByID(CommonConstants.QUERY_ID_ALL_RawMaterials));
		}
		ResultSet resultSet = preparedstatement.executeQuery();
		
		while(resultSet.next()) {
			
			rawMaterial raw_Material = new rawMaterial();
			
			raw_Material.setRawMaterialID(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
			raw_Material.setRawMaterialName(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
			raw_Material.setSupplierID(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
			raw_Material.setStoreID(resultSet.getString(CommonConstants.COLUMN_INDEX_FOUR));
			raw_Material.setUnitPrice(resultSet.getDouble(CommonConstants.COLUMN_INDEX_FIVE));
			rawMaterialList.add(raw_Material);
			
		}
	} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
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
	return rawMaterialList;
}

/**
 * Get the updated raw material
 * 
 * @param rawMaterialID
 *            ID of the raw material to remove or select from the list
 * 
 * @return return the raw material object
 * 
 */
}
