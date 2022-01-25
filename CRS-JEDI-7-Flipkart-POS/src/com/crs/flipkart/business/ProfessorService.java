/**
 * 
 */
package com.crs.flipkart.business;

import java.util.List;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.dao.CatalogDaoInterface;
import com.crs.flipkart.dao.CatalogDaoService;

/**
 * @author Shubham
 *
 */
public class ProfessorService implements ProfessorInterface {

	CatalogDaoInterface catalogService = new CatalogDaoService();
	
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
