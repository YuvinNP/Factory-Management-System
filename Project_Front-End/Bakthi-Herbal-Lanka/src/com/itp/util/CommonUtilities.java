package com.itp.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;



public class CommonUtilities {
	
	public static final Properties properties = new Properties();
	
	static{
		
		try {
			
			properties.load(QueryUtilities.class.getResourceAsStream(CommonConstants.PROPERTY));
			
		} catch (IOException e) {
			
			System.out.println(e.getMessage());
		}
	}
	
	
	public static String generateSupplierID(ArrayList<String> arrayList) {

		String id;
		int next = arrayList.size();
		next++;
		id = CommonConstants.SUPPLIER_ID_PRIFIX + next;
		if (arrayList.contains(id)) {
			next++;
			id = CommonConstants.SUPPLIER_ID_PRIFIX + next;
		}
		return id;
	}
	
	public static String generateEmployeeID(ArrayList<String> arrayList) {

		String id;
		int next = arrayList.size();
		next++;
		id = CommonConstants.EMPLOYEE_ID_PRIFIX + next;
		if (arrayList.contains(id)) {
			next++;
			id = CommonConstants.EMPLOYEE_ID_PRIFIX + next;
		}
		return id;
	}
	
	public static String generateLoginID(ArrayList<String> loginList) {

		String id;
		int next = loginList.size();
		next++;
		id = CommonConstants.EMPLOYEE_ID_PRIFIX + next;
		if (loginList.contains(id)) {
			next++;
			id = CommonConstants.EMPLOYEE_ID_PRIFIX + next;
		}
		return id;
	}
	
	public static String generateLeaveID(ArrayList<String> leaveList) {

		String id;
		int next = leaveList.size();
		next++;
		id = CommonConstants.LOGIN_ID_PRIFIX + next;
		if (leaveList.contains(id)) {
			next++;
			id = CommonConstants.LOGIN_ID_PRIFIX + next;
		}
		return id;
	}
}
