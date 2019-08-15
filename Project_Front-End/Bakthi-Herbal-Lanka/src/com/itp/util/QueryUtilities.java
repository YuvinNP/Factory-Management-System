package com.itp.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;




public class QueryUtilities {

public static String queryByID(String id) throws SAXException, IOException, ParserConfigurationException {
		
		NodeList nodelist;
		Element element = null;
		
		nodelist = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(System.getProperty("catalina.base") + "\\wtpwebapps\\KYNK Hospitals\\WEB-INF\\HospitalQuery.xml"))
					.getElementsByTagName(CommanConstants.XML_TAG_NAME);
		

		for (int value = 0; value < nodelist.getLength(); value++) {
			element = (Element) nodelist.item(value);
			if (element.getAttribute(CommanConstants.ATTR_ID).equals(id))
				break;
		}
		return element.getTextContent().trim();
		
	}
	
}
