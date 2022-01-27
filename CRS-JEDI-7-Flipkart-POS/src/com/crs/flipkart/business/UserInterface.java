/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.bean.User;

/**
 * @author jasan
 *
 */
public interface UserInterface {
	User login(String emailId, String password);

	String registerStudent(String name, String contactNumber, String email, String password, String branch, String batch);

	String updatePassword(String email, String oldPassword, String newPassword);

}

