/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.bean.User;
import com.crs.flipkart.exceptions.EmailAlreadyInUseException;
import com.crs.flipkart.exceptions.UserNotFoundException;

/**
 * @author jasan
 *
 */
public interface UserInterface {
	User login(String emailId, String password) throws UserNotFoundException;

	String registerStudent(String name,String contactNumber, String email, String password, String branch, String batch) throws EmailAlreadyInUseException;

	String updatePassword(String email, String oldPassword, String newPassword) throws UserNotFoundException;

}

