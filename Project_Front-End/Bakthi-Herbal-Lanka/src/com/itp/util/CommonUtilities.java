package com.itp.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;



public class CommonUtilities {
	
//	public static final Logger log1 = Logger.getLogger(IDoctorServices.class.getName());
//	public static final Logger log2 = Logger.getLogger(IAppoinmentServices.class.getName());
//	public static final Logger log3 = Logger.getLogger(IPatientServices.class.getName());
//	public static final Logger log4 = Logger.getLogger(IFeedbackServices.class.getName());
	

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
}
