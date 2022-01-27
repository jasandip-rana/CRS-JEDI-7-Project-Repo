/**
 * 
 */
package com.crs.flipkart.business;

import java.util.List;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.dao.*;

/**
 * @author Shubham
 *
 */
public class AdminService implements AdminInterface {

	
	AdminDaoInterface adminDaoService=new AdminDaoService();
	public List<Course> viewCourse()
	{
		return adminDaoService.viewCourses();
	}
	
	public String addCourse(Course newCourse)
	{
		return adminDaoService.addCourse(newCourse);
	}
	
	public String dropCourse(String courseId)
	{
		return adminDaoService.dropCourse(courseId);
	}
	
	public String addProfessor(Professor newProfessor)
	{
		return adminDaoService.addProfessor(newProfessor);
	}
	
	public String dropProfessor(String professorId)
	{
		return adminDaoService.dropProfessor(professorId);
	}
	
	public String generateGradeCard(String studentId, String semester)
	{
		return adminDaoService.generateGradeCard(studentId, semester);
	}
	public List<Student> getPendingStudents()
	{
		return adminDaoService.getPendingStudents();
	}
	public String approveStudent(Student newStudent)
	{
		return adminDaoService.approveStudent(newStudent);
	}

	@Override
	public List<Professor> viewProfessorList() {
		// TODO Auto-generated method stub
		return adminDaoService.viewProfessorList();
	}
	
}
