package com.crs.flipkart.dao;

import java.util.List;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;

public interface AdminDaoInterface {

	List<Course> viewCourses();

	String addCourse(Course newCourse);

	String dropCourse(String courseId);

	String approveStudent(Student newStudent);

	List<String> viewProfessorList();

	String generateGradeCard(String studentId, String semester);

}