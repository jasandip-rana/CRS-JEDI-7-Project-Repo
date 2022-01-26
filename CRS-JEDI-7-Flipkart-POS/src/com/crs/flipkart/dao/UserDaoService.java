/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import com.crs.flipkart.bean.User;
import com.crs.flipkart.constants.SQLQueries;
import com.crs.flipkart.utils.dbUtil;

/**
 * @author Shubham
 *
 */
public class UserDaoService implements UserDaoInterface {

	public static Connection conn = dbUtil.getConnection();

	@Override
	public User login(String emailId, String password) {
		try {
			PreparedStatement ps = conn.prepareStatement(SQLQueries.GET_USER_EMAIL_PASSWORD);
			ps.setString(1, emailId);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			User user = null;
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getString("userId"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getInt("role"));
			}
			return user;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String createUser(String name, String email, String password, String role) {

		try {

			PreparedStatement ps = conn.prepareStatement(SQLQueries.VERIFY_EMAIL);

			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return "Email already in use";
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return "User not created";
		}

		try {

			PreparedStatement ps = conn.prepareStatement(SQLQueries.ADD_USER_QUERY);
			Random rand = new Random();
			long id = 10000000 + rand.nextInt(10000000);
			String userId = role.charAt(0) + Long.toString(id);
			ps.setString(1, userId);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setString(4, password);
			if (role.equals("Professor")) {
				ps.setInt(5, 1);
			} else if (role.equals("Student")) {
				ps.setInt(5, 2);
			} else
				ps.setInt(5, 0);

			ps.executeUpdate();
			return userId;
		} catch (SQLException e) {
			e.printStackTrace();
			return "User not created";
		}
	}

	@Override
	public String registerStudent(String name, String email, String password, String branch, String batch) {
		try {
			String id = createUser(name, email, password, "Student");
			if (id.equals("User not created") || id.equals("Email already in use")) {
				return id;
			} else {
				PreparedStatement ps = conn.prepareStatement(SQLQueries.ADD_STUDENT);
				ps.setString(1, id);
				ps.setString(2, branch);
				ps.setString(3, batch);
				ps.setInt(4, 0);
				ps.setInt(5, 0);
				
				System.out.println("ID = "+id);
				
				if (ps.executeUpdate() == 1)
					return "Account created";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "User not created";
		}
		return "User not created";
	}

	@Override
	public String updatePassword(String email, String oldPassword, String newPassword) {
		try {
			PreparedStatement ps = conn.prepareStatement(SQLQueries.UPDATE_PASSWORD);
			ps.setString(2, email);
			ps.setString(3, oldPassword);
			ps.setString(1, newPassword);
			int rowAffected = ps.executeUpdate();
			if (rowAffected == 1)
				return "Password Updated";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Password not updated";
		}
		return "Password not updated";
	}
}
