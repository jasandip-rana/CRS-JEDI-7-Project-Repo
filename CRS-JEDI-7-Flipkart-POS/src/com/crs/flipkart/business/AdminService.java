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

	
	AdminDaoInterface adminService=new AdminDaoService();
	public List<Course> viewCourse()
	{
		return adminService.viewCourses();
	}
	
	public String addCourse(Course newCourse)
	{
		return adminService.addCourse(newCourse);
	}
	
	public String dropCourse(String courseId)
	{
		return adminService.dropCourse(courseId);
	}
	
	public String addProfessor(Professor newProfessor)
	{
		return "";
	}
	
	public String dropProfessor(String professorId)
	{
		return "";
	}
	
	public String generateGradeCard(String studentId, String semester)
	{
		return adminService.generateGradeCard(studentId, semester);
	}
	
	public String approveStudent(Student newStudent)
	{
		return adminService.approveStudent(newStudent);
	}

	@Override
	public List<String> viewProfessorList() {
		// TODO Auto-generated method stub
		return adminService.viewProfessorList();
	}
	
}
