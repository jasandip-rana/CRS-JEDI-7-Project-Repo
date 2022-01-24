/**
 * 
 */
package com.crs.flipkart.business;

import java.util.List;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Student;

/**
 * @author Shubham
 *
 */
public class ProfessorService implements ProfessorInterface {

	CatalogInterface catalogService = new CatalogService();
	
	public List<Course> viewCourses()
	{
		List<Course> courseList = catalogService.viewCourses();
		
		for(Course course : courseList)
		{
			if(course.getProfessorId() != null)
				courseList.remove(course);
		}
		
		return courseList;
	}
	
	public String indicateCourse(String courseId)
	{
		return "";
	}
	
	public String gradeStudent(String studentId, String courseId, float grade)
	{
		return "";
	}
	
	public List<Student> viewEnrolledStudents(String courseId)
	{
		return null;
	}
}
