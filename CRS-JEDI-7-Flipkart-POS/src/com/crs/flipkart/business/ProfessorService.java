/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;
import java.util.List;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.dao.CatalogDaoInterface;
import com.crs.flipkart.dao.CatalogDaoService;
import com.crs.flipkart.dao.ProfessorDaoInterface;
import com.crs.flipkart.dao.ProfessorDaoService;

/**
 * @author Shubham
 * Implementation of Professor Services
 */
public class ProfessorService implements ProfessorInterface {

	ProfessorDaoInterface professorService = new ProfessorDaoService();
	
	/**
	 * Method for getting the list of all courses available for the professor to choose
	 * 
	 * @return returns a list of all the courses where professorId is null
	 */
	public List<Course> viewCourses()
	{
		List<Course> courseList = professorService.viewCourses();
		List<Course> newCourseList = new ArrayList<Course> ();
		
		for(Course course : courseList)
		{
			if(course.getProfessorId() == null)
				newCourseList.add(course);
		}
		
		return newCourseList;
	}
	
	/**
	 * Method for getting the list of all courses selected by the professor
	 * 
	 * @param professorId unique Id to represent a professor
	 * @return returns a list of all the courses where professorId matches the professorId provided 
	 */
	public List<Course> viewSelectedCourses(String professorId)
	{
		List<Course> courseList = professorService.viewCourses();
		List<Course> newCourseList = new ArrayList<Course> ();
		
		for(Course course : courseList)
		{
			if(course.getProfessorId() != null && course.getProfessorId().equals(professorId))
				newCourseList.add(course);
		}
		
		return newCourseList;
	}
	
	/**
	 * Method for the professor to indicate their courses
	 * 
	 * @param professorId unique Id to represent a professor
	 * @param courseId unique Id to represent a course
	 * @return returns a string indicating if a course is successfully alloted
	 */
	public String indicateCourse(String professorId,String courseId)
	{
		return professorService.selectCourse(professorId, courseId);
	}
	
	/**
	 * Method for the professor to grade a student for a particular course
	 * 
	 * @param studentId unique Id to represent a student
	 * @param courseId unique Id to represent a course
	 * @param grade floating point number that represents the grade provided for the course
	 * @param semester indicates the semester
	 * @return returns a string indicating if the student was graded successfully
	 */
	public String gradeStudent(String studentId, String courseId, float grade,String semester)
	{
		return professorService.addGrade(studentId, courseId, grade, semester);
	}
	
	/**
	 * Method to view all the students enrolled in the course
	 * 
	 * @param courseId unique Id to represent a course
	 * @return returns a list of all the students enrolled in the course
	 */
	public List<String> viewEnrolledStudents(String courseId)
	{
		return professorService.viewEnrolledStudents(courseId);
	}
	
	/**
	 * Method to check whether the course is taught by the professor or not
	 * 
	 * @param courseId unique Id to represent a course
	 * @param professorId unique Id to represent a professor
	 * @return returns true if the course is taught by the professor otherwise false
	 */
	public boolean validateCourse(String courseId, String professorId) 
	{
		List<Course> courseList = professorService.viewCourses();
		
		for(Course course : courseList)
		{
			if(course.getProfessorId() != null && course.getProfessorId().equals(professorId) && course.getCourseId().equals(courseId))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Method to check the student is enrolled in the course
	 * 
	 * @param courseId unique Id to represent a course
	 * @param studentId unique Id to represent a student
	 * @return returns true if the student is enrolled in the course
	 */
	public boolean validateStudent(String courseId, String studentId) 
	{
		List<String> enrolledStudents = professorService.viewEnrolledStudents(courseId);
		
		for(String student: enrolledStudents)
		{
			if(studentId.equals(student.substring(0,9)))
				return true;
		}
		
		return false;
	}
}

