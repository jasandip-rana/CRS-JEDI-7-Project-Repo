/**
 * 
 */
package com.crs.flipkart.business;

import java.util.List;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Student;

/**
 * @author jasan
 * ProfessorService business interface
 */
public interface ProfessorInterface {

	/**
	 * Method for getting the list of all courses available for the professor to choose
	 * 
	 * @return returns a list of all the courses where professorId is null
	 */
	public List<Course> viewCourses();
	
	/**
	 * Method for getting the list of all courses selected by the professor
	 * 
	 * @param professorId unique Id to represent a professor
	 * @return returns a list of all the courses where professorId matches the professorId provided 
	 */
	public List<Course> viewSelectedCourses(String professorId);
	
	/**
	 * Method for the professor to indicate their courses
	 * 
	 * @param professorId unique Id to represent a professor
	 * @param courseId unique Id to represent a course
	 * @return returns a string indicating if a course is successfully alloted
	 */
	public String indicateCourse(String professorId,String courseId);
	
	/**
	 * Method for the professor to grade a student for a particular course
	 * 
	 * @param studentId unique Id to represent a student
	 * @param courseId unique Id to represent a course
	 * @param grade floating point number that represents the grade provided for the course
	 * @param semester indicates the semester
	 * @return returns a string indicating the student was graded successfully
	 */
	public String gradeStudent(String studentId, String courseId, float grade,String semester);
	
	/**
	 * Method to view all the students enrolled in the course
	 * 
	 * @param courseId unique Id to represent a course
	 * @return returns a list of all the students enrolled in the course
	 */
	public List<String> viewEnrolledStudents(String courseId);
	
	/**
	 * Method to check whether the course is taught by the professor or not
	 * 
	 * @param courseId unique Id to represent a course
	 * @param professorId unique Id to represent a professor
	 * @return returns true if the course is taught by the professor otherwise false
	 */
	public boolean validateCourse(String courseId, String professorId);
	
	/**
	 * Method to check the student is enrolled in the course
	 * 
	 * @param courseId unique Id to represent a course
	 * @param studentId unique Id to represent a student
	 * @return returns true if the student is enrolled in the course
	 */
	public boolean validateStudent(String courseId, String studentId);
}
