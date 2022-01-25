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

	CatalogDaoInterface courseList = new CatalogDaoService();
	StudentInterface student = new StudentService();
	ProfessorInterface professor = new ProfessorService();
	
	public List<Course> viewCourse()
	{
		return null;
	}
	
	public String addCourse(Course newCourse)
	{
		return "";
	}
	
	public String dropCourse(String courseId)
	{
		return "";
	}
	
	public String addProfessor(Professor newProfessor)
	{
		return "";
	}
	
	public String dropProfessor(String professorId)
	{
		return "";
	}
	
	public String generateGradeCard(String studentId)
	{
		return "";
	}
	
	public String approveStudent(Student newStudent)
	{
		return "";
	}

	@Override
	public List<Professor> viewProfessorList() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
