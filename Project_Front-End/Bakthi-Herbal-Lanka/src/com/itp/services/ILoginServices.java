
/**
 * 
 */
package com.itp.services;

import java.util.ArrayList;

import com.itp.model.Employee;
import com.itp.model.Login;

/**
 * @author Yuvin Nimnaka
 *
 */
public interface ILoginServices {

	public void addLogin(Login login);

	
	public Employee getLoginByID(String empoyeeID);
	
	
	public ArrayList<Login> getLogin();
	
	
	public Employee updateLogin(String loginID, Login login);

	
	public void removeLogin(String loginID);
}
