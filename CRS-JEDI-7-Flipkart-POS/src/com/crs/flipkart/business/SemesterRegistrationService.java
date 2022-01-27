package com.crs.flipkart.business;

import java.util.*;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.dao.*;

public class SemesterRegistrationService implements SemesterRegistrationInterface{

	CatalogDaoInterface catalogService = new CatalogDaoService();
	SemesterRegistrationDaoInterface semesterRegistrationDaoService = new SemesterRegistrationDaoService();

	@Override
	public List<Course> viewCourses() {
		return catalogService.viewCourses();
	}
	
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
	
	@Override
	public String addCourse(String studentId, String courseId) {
		// TODO Auto-generated method stub
		if(!verifyCourse(courseId))
		{
			return "No such course exists";
		}
		List<Course> optedCourses = semesterRegistrationDaoService.viewOptedCourses(studentId);
		if (optedCourses.size() >= 6)
			return "4 primary and 2 secondary courses have already been opted.";
		for (Course optedCourse : optedCourses) {
			if (optedCourse.getCourseId().equals(courseId)) {
				return "Course already opted";
			}
		}
		return semesterRegistrationDaoService.addCourse(studentId, courseId);
	}

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

	@Override
	public List<Course> viewOptedCourses(String studentId) {
		return semesterRegistrationDaoService.viewOptedCourses(studentId);
	}

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
