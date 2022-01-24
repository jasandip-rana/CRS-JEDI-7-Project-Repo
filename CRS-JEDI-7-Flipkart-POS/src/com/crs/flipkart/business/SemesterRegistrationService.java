package com.crs.flipkart.business;

import java.util.*;

import com.crs.flipkart.bean.Course;

public class SemesterRegistrationService implements SemesterRegistrationInterface{

	
	CatalogInterface catalogService = new CatalogService();
	
	List<Course> optedCourses = new ArrayList<Course>();

	public List<Course> viewCourses()
	{
		List<Course> courseList = catalogService.viewCourses();
		
		for(Course course : courseList)
		{
			if(course.getRegisteredStudents().size() >= 10)
				courseList.remove(course);
		}
		
		return courseList;
	}
	
	public String addCourse(String studentID, String courseID) {
		// TODO Auto-generated method stub
		for(Course course : catalogService.viewCourses())
		{
			if(course.getCourseId().equals(courseID))
			{
				optedCourses.add(course);
				return "Course successfully opted.";
			}
		}
		
		return "No such course found. Try again.";
	}

	public String dropCourse(String studentID, String courseID) {
		// TODO Auto-generated method stub
		for(Course course : this.optedCourses)
		{
			if(course.getCourseId().equals(courseID))
			{
				this.optedCourses.remove(course);
				return "Course successfully removed.";
			}
		}
		
		return "No such course found. Try again.";
	}

	public List<Course> viewOptedCourses(String studentID) {
		// TODO Auto-generated method stub
		return this.optedCourses;
		
	}

	public String submitChoice(String studentID) {
		// TODO Auto-generated method stub
		return null;
	}

}
