package com.crs.flipkart.dao;

import com.crs.flipkart.bean.User;

public interface UserDaoInterface {

	User login(String emailId, String password);

	String createUser(String name, String email, String password, String role);

	String registerStudent(String name, String email, String password, String branch, String batch);

	String updatePassword(String email, String oldPassword, String newPassword);

}