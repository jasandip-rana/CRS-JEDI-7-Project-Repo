/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.User;
import com.crs.flipkart.constants.SQLQueries;
import com.crs.flipkart.exceptions.EmailAlreadyInUseException;
import com.crs.flipkart.exceptions.UserNotFoundException;
import com.crs.flipkart.utils.dbUtil;

/**
 * @author Shubham
 *
 */
public class UserDaoService implements UserDaoInterface {

	private static Logger logger = Logger.getLogger(UserDaoService.class);
	public static Connection conn = dbUtil.getConnection();

	/**
	 * Method to check if a user with given email-id and password exists
	 * 
	 * @param emailId of the user
	 * @param password of the user
	 * @return returns a User containing user info id found in database or returns null
	 */
	@Override
	public User login(String emailId, String password) throws UserNotFoundException{
		try {
			PreparedStatement ps = conn.prepareStatement(SQLQueries.GET_USER_EMAIL_PASSWORD);
			ps.setString(1, emailId);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			User user = null;
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getString("user.userId"));
				user.setRole(rs.getString("role"));
				return user;
			}
			else {
				throw new UserNotFoundException(emailId);
			}

		} catch (SQLException e) {
			logger.debug("Error: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Method to enter a new user in the database
	 * 
	 * @param name of the user
	 * @param emailId of the user
	 * @param password of the user
	 * @param role of the user
	 * @return returns a string that indicates if the user is successfully entered in the database
	 */
	@Override
	public String createUser(String name, String email, String password, String role) throws EmailAlreadyInUseException{

		try {

			PreparedStatement ps = conn.prepareStatement(SQLQueries.VERIFY_EMAIL);

			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				throw new EmailAlreadyInUseException(email);
			}

		} catch (SQLException e) {
			logger.debug("Error: " + e.getMessage());
			return "User not created";
		}

		try {

			PreparedStatement ps = conn.prepareStatement(SQLQueries.ADD_USER_QUERY);
			Random rand = new Random();
			long id = 10000000 + rand.nextInt(10000000);
			String userId = role.charAt(0) + Long.toString(id);
			ps.setString(1, userId);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.executeUpdate();
			ps = conn.prepareStatement(SQLQueries.ADD_USER_ROLE);
			ps.setString(1,userId);
			ps.setString(2, role);
			ps.executeUpdate();
			return userId;
		} catch (SQLException e) {
			logger.debug("Error: " + e.getMessage());
			return "User not created";
		}
	}


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
	@Override
	public String registerStudent(String name, String contactNumber, String email, String password, String branch, String batch) throws EmailAlreadyInUseException{
		try {
			String id = createUser(name, email, password, "Student");
			if (id.equals("User not created")) {
				return id;
			} else {
				PreparedStatement ps = conn.prepareStatement(SQLQueries.ADD_STUDENT);
				ps.setString(1, id);
				ps.setString(2, name);
				ps.setString(3, contactNumber);
				ps.setString(4, branch);
				ps.setString(5, batch);
				ps.setInt(6, 0);
				ps.setInt(7, 0);
				
				System.out.println("ID = "+id);
				
				if (ps.executeUpdate() == 1)
					return "Account created";
			}
		}
		catch(EmailAlreadyInUseException e) {
			throw e;
		}
		catch (Exception e) {
			logger.debug("Error: " + e.getMessage());
			return "User not created";
		}
		return "User not created";
	}


	/**
	 * Method to update password of the user
	 * 
	 * @param emailId of the user
	 * @param old password of the user
	 * @param new password of the user
	 * @return returns a string that indicates if the password is changed successfully
	 */
	@Override
	public String updatePassword(String email, String oldPassword, String newPassword) throws UserNotFoundException{
		try {
			PreparedStatement ps = conn.prepareStatement(SQLQueries.UPDATE_PASSWORD);
			ps.setString(2, email);
			ps.setString(3, oldPassword);
			ps.setString(1, newPassword);
			int rowAffected = ps.executeUpdate();
			if (rowAffected == 1)
				return "Password Updated";
			else 
				throw new UserNotFoundException(email);
		} catch (SQLException e) {
			logger.debug("Error: " + e.getMessage());
			return "Password not updated";
		}
	}
}
