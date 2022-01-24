/**
 * 
 */
package com.crs.flipkart.business;

import java.util.List;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Student;

/**
 * @author jasan
 *
 */
public interface ProfessorInterface {

	public List<Course> viewCourses();
	public String indicateCourse(String courseId);
	public String gradeStudent(String studentId, String courseId, float grade);
	public List<Student> viewEnrolledStudents(String courseId);
}
