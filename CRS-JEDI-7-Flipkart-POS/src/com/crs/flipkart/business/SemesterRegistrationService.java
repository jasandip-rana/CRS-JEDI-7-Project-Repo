package com.crs.flipkart.business;

import java.util.*;

import com.crs.flipkart.bean.Course;

public class SemesterRegistrationService {

	Course course1 =  new Course("101","Mathematics","Computer Science","P01",new ArrayList<String>(),(float) 1000.5);
	Course course2 =  new Course("102","English","Languages","P02",new ArrayList<String>(),(float) 1500.00);
	Course course3 =  new Course("103","Political Science","Law","P03",new ArrayList<String>(),(float) 500.75);
	
	ArrayList<Course> optedCourses = new ArrayList<Course>();
	
	ArrayList<Course> courseList = new ArrayList<Course>() {
		{add(course1); add(course2); add(course3);}
	};
	
	
	public List<Course> viewCourses() {
		// TODO Auto-generated method stub
		return courseList;
	}

	public String addCourse(String studentID, String courseID) {
		// TODO Auto-generated method stub
		for(Course course : courseList)
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
