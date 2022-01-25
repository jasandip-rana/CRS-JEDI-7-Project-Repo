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
	public static final String ADD_COURSE = "insert into course(courseId, courseName, courseFee, department) values (?, ?, ?, ?)";
	
	// Query condition removed --> studentCount = 0
	public static final String DROP_COURSE = "delete from Course where id = ?";
	
	public static final String APPROVE_ADDMISSION_REQUEST = "UPDATE student SET isApproved = 1 where id = ?";
	
	// Query condition modified --> Join removed
//	public static final String VIEW_PROFESSOR = "SELECT * FROM professor";
	
//	public static final String LIST_PROFESSORS = "SELECT professor.id, user.name, user.email, professor.department, professor.designation FROM professor INNER JOIN user ON professor.userId = user.id";

	public static final String GET_GRADES = "SELECT * from grade INNER JOIN course ON grade.courseId = course.courseId WHERE grade.studentId = ? AND grade.semester = ?";

	public static final String GENERATE_GRADES = "insert into gradeCard(studentId, semester, gpa) values (?,?,?)";

	public static final String SELECT_COURSE_FOR_PROF = "UPDATE course SET professorId = ? WHERE courseId = ?";

	public static final String CHECK_VACANT_COURSE = "SELECT professorId from course WHERE courseId = ?";

	public static final String ADD_GRADE = "INSERT INTO grade(studentId, courseId, gpa, semester) values(?, ?, ?, ?)";

	public static final String VIEW_ENROLLED_STUDENTS = "SELECT user.userId, user.name from user INNER JOIN optedCourses ON user.userId = optedCourses.studentId WHERE optedCourses.isAlloted = 1 AND optedCourses.courseId = ?"; 
}
