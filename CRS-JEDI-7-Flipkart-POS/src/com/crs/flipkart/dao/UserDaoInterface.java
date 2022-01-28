package com.crs.flipkart.dao;

import com.crs.flipkart.bean.User;
import com.crs.flipkart.exceptions.EmailAlreadyInUseException;
import com.crs.flipkart.exceptions.UserNotFoundException;

public interface UserDaoInterface {

	/**
	 * Method to check if a user with given email-id and password exists
	 * 
	 * @param emailId of the user
	 * @param password of the user
	 * @return returns a User containing user info id found in database or returns null
	 */
	User login(String emailId, String password)  throws UserNotFoundException;

	/**
	 * Method to enter a new user in the database
	 * 
	 * @param name of the user
	 * @param emailId of the user
	 * @param password of the user
	 * @param role of the user
	 * @return returns a string that indicates if the user is successfully entered in the database
	 */
	String createUser(String name, String email, String password, String role) throws EmailAlreadyInUseException;

	/**
	 * Method to enter a new student in the database
	 * 
	 * @param name of the user
	 * @param contact number of the user
	 * @param emailId of the user
	 * @param password of the user
	 * @param branch of the user
	 * @param batch of the user
	 * @return returns a string that indicates if the student is successfully entered in the database
	 */
	String registerStudent(String name, String contactNumber, String email, String password, String branch, String batch) throws EmailAlreadyInUseException;

	/**
	 * Method to update password of the user
	 * 
	 * @param emailId of the user
	 * @param old password of the user
	 * @param new password of the user
	 * @return returns a string that indicates if the password is changed successfully
	 */
	String updatePassword(String email, String oldPassword, String newPassword) throws UserNotFoundException;

}