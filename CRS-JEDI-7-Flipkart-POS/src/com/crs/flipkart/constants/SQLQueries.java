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
	
	public static final String ADD_PAYMENT = "insert into payment(studentId,referenceId,amount,paymentMode) values (?,?, ?, ?)";
	
	public static final String FETCH_GRADECARD = "SELECT * FROM gradecard WHERE studentId = ? ";
	public static final String FETCH_GRADE= "SELECT * FROM grade WHERE studentId = ? ";
	
	public static final String VERIFY_EMAIL= "SELECT * FROM user where email = ?";
	public static final String IS_APPROVED= "SELECT * FROM student where studentId= ?";



}
