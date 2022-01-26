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
	public static final String ADD_OPTED_COURSE = "insert into optedcourses (studentId,isAlloted,courseId) values ( ?, ?, ?)";
	public static final String FETCH_OPTED_COURSES = "Select course.courseId as courseId,courseName,courseFee,department,professorId,studentCount,optedNumber from course join optedcourses where course.courseId = optedcourses.courseId and studentId = ? order by optedcourses.optedNumber";
	public static final String DROP_OPTED_COURSE = "delete from optedcourses where studentId=? AND courseId = ?";
	public static final String SUBMIT_OPTED_COURSES = "UPDATE optedcourses SET isAlloted = 1 WHERE studentId = ?";
	public static final String INCREMENT_STUDENT_COUNT = "UPDATE course SET studentCount=? where courseId=?";
	


}
