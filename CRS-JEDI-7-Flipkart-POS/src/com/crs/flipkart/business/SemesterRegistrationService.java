package com.crs.flipkart.business;

import java.util.*;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.dao.*;
import com.crs.flipkart.exceptions.CourseAlreadyOptedException;
import com.crs.flipkart.exceptions.CourseCountExceededException;
import com.crs.flipkart.exceptions.CourseNotFoundException;

public class SemesterRegistrationService implements SemesterRegistrationInterface{

	CatalogDaoInterface catalogService = new CatalogDaoService();
	SemesterRegistrationDaoInterface semesterRegistrationDaoService = new SemesterRegistrationDaoService();

	
	 /**
     * method for getting all the courses from course catalog
     * 
     * 
     * @return list of course 
     */
	@Override
	public List<Course> viewCourses() {
		return catalogService.viewCourses();
	}
	
	 /**
     * method for verifying course exists or not in the catalog
     * 
     * @param courseId  unique Id to represent a course
     * @return returns true if course exists in the catalog.
     */
	@Override
	public boolean verifyCourse(String courseId)
	{
		for (Course course : catalogService.viewCourses()) {
			if (course.getCourseId().equals(courseId)) {
				return true;
			}
		}
		return false;
	}
	
	 /**
     * method for adding course for the student 
     *
     * @param studentId  unique Id to represent a student
     * @param courseId  unique Id to represent a course
     * @return returns String which represents the status of adding course 
     */
	@Override
	public void addCourse(String studentId, String courseId) throws CourseNotFoundException, CourseCountExceededException, CourseAlreadyOptedException{
		// TODO Auto-generated method stub
		List<Course> optedCourses = semesterRegistrationDaoService.viewOptedCourses(studentId);
		if (optedCourses.size() >= 6)
			throw new CourseCountExceededException(6);
		for (Course optedCourse : optedCourses) {
			if (optedCourse.getCourseId().equals(courseId)) {
				throw new CourseAlreadyOptedException(courseId);
			}
		}
		try {			
			semesterRegistrationDaoService.addCourse(studentId, courseId);
		}
		catch(CourseNotFoundException e) {
			throw e;
		}
	}
	
	/**
     * method for dropping course for the student 
     *
     * @param studentId  unique Id to represent a student
     * @param courseId  unique Id to represent a course
     * @return returns String which represents the status of dropping course 
     */

	@Override
	public String dropCourse(String studentId, String courseId) {
		// TODO Auto-generated method stub
		if(!verifyCourse(courseId))
		{
			return "No such course exists";
		}
		List<Course> optedCourses = semesterRegistrationDaoService.viewOptedCourses(studentId);
		for(Course optedCourse : optedCourses)
		{
			if(optedCourse.getCourseId().equals(courseId))
			{
				return semesterRegistrationDaoService.dropCourse(studentId,courseId);
			}
		}
		return "You have not opted this course";
	}

	/**
     * method for getting all opted courses by the student 
     *
     * @param studentId  unique Id to represent a student
     * @return list of courses opted by the student 
     */
	@Override
	public List<Course> viewOptedCourses(String studentId) {
		return semesterRegistrationDaoService.viewOptedCourses(studentId);
	}
	
	/**
     * method for submitting the course choices of the student 
     *
     * @param studentId  unique Id to represent a student
     * @return returns String which represents the status of submitting course choices 
     */

	@Override
	public String submitOptedCourses(String studentId) {
		// TODO Auto-generated method stub
		List<Course> optedCourses = semesterRegistrationDaoService.viewOptedCourses(studentId);
		if(optedCourses.size()<4)
			return "Please select atleast 4 courses";
		int count=0;
		String end="You have registered for : \n";
		for(Course course:optedCourses)
		{
			if(count<4 & course.getStudentCount()<10)
			{
				count++;
				end+=course.getCourseId()+" : "+course.getCourseName()+"\n";
			}
			else
			{
				semesterRegistrationDaoService.dropCourse(studentId, course.getCourseId());
			}
		}
		if(count<4)
			return "Please select some other courses as some courses are filled and dropped from the list";
		return semesterRegistrationDaoService.submitOptedCourses(studentId)+"\n"+end;
	}

}
