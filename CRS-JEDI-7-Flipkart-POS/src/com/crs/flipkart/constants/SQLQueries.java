/**
 * 
 */
package com.crs.flipkart.constants;

/**
 * @author Shubham
 *
 */
public class SQLQueries {
	public static final String ADD_USER_QUERY = "insert into user(userId,name,email,password,role) values (?,?, ?, ?, ?)";
	public static final String ADD_STUDENT = "insert into student(studentId,branch,batch,verificationStatus,feeStatus) values (?, ?, ?, ?, ?)";
	public static final String FETCH_COURSES = "select * from course";
	public static final String GET_USER_EMAIL_PASSWORD = "SELECT * FROM user WHERE email = ? AND password = ?";
	public static final String UPDATE_PASSWORD = "UPDATE user SET password = ? WHERE email = ? and password = ?";


}
