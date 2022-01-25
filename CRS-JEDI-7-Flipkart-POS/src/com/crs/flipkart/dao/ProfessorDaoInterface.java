package com.crs.flipkart.dao;

import java.util.List;

import com.crs.flipkart.bean.Course;

public interface ProfessorDaoInterface {

	List<Course> viewCourses();

	String selectCourse(String professorId, String courseId);

	String addGrade(String studentId, String courseId, float grade, String semester);

	List<String> viewEnrolledStudents(String courseId);

}