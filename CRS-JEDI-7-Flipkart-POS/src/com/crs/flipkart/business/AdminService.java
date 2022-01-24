/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;

/**
 * @author Shubham
 *
 */
public class AdminService implements AdminInterface {

	CatalogInterface courseList = new CatalogService();
	StudentInterface student = new StudentService();
	ProfessorInterface professor = new ProfessorService();
	
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
	
}
