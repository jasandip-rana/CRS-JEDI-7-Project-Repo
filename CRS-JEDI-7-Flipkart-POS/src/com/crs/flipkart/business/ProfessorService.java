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
 *
 */
public class ProfessorService implements ProfessorInterface {

	ProfessorDaoInterface professorService = new ProfessorDaoService();
	
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
	
	public String indicateCourse(String professorId,String courseId)
	{
		return professorService.selectCourse(professorId, courseId);
	}
	
	public String gradeStudent(String studentId, String courseId, float grade,String semester)
	{
		return professorService.addGrade(studentId, courseId, grade, semester);
	}
	
	public List<String> viewEnrolledStudents(String courseId)
	{
		return professorService.viewEnrolledStudents(courseId);
	}
	
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

