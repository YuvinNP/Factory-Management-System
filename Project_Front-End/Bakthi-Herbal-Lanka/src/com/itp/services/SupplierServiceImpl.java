package com.itp.services;

import java.sql.Connection;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * @author HP
 *
 */

public class SupplierServiceImpl implements ISupplierServices {


	/** Initialize logger */
	public static final Logger log = Logger.getLogger(SupplierServiceImpl.class.getName());

	private static Connection connection;

	private static Statement statement;
	
	

}
