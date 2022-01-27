/**
 * 
 */
package com.crs.flipkart.business;

import java.sql.*;
import com.crs.flipkart.bean.*;
import com.crs.flipkart.dao.*;

/**
 * @author Shubham
 *
 */
public class UserService implements UserInterface {
	UserDaoInterface userDaoService = new UserDaoService();
	public User login(String emailId, String password)
	{
		return userDaoService.login(emailId, password);
	}

	public String registerStudent(String name,String contactNumber, String email, String password, String branch, String batch)
	{
		return userDaoService.registerStudent(name,contactNumber, email, password, branch, batch);
	}

	public String updatePassword(String email, String oldPassword, String newPassword)
	{
		return userDaoService.updatePassword(email, oldPassword, newPassword);
	}
}
