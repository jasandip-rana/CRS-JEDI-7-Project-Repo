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
	public List<Course> viewSelectedCourses(String professorId);
	public String indicateCourse(String professorId,String courseId);
	public String gradeStudent(String studentId, String courseId, float grade,String semester);
	public List<String> viewEnrolledStudents(String courseId);
	public boolean validateCourse(String courseId, String professorId);
	public boolean validateStudent(String courseId, String studentId);
}
