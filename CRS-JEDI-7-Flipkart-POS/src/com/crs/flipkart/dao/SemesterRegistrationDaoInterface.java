package com.crs.flipkart.dao;

import java.util.List;

import com.crs.flipkart.bean.Course;

public interface SemesterRegistrationDaoInterface {

	String addCourse(String studentId, String courseId);

	List<Course> viewOptedCourses(String studentId);

	String dropCourse(String studentId, String courseId);

	String submitOptedCourses(String studentId);

}