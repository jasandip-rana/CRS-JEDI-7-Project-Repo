package com.crs.flipkart.dao;

import java.util.List;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;

public interface AdminDaoInterface {

	List<Course> viewCourses();

	String addCourse(Course newCourse);

	String dropCourse(String courseId);
	
	List<Student> getPendingStudents();

	String approveStudent(Student newStudent);

	List<Professor> viewProfessorList();
	
	String addProfessor(Professor newProfessor);
	
	String dropProfessor(String professorId);

	String generateGradeCard(String studentId, String semester);

}