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
 * Implementation of Admin Interface
 */
public class AdminService implements AdminInterface {

	
	AdminDaoInterface adminDaoService=new AdminDaoService();
	
	/**
     * method for viewing all courses in the catalog
     *
     * @return returns List of all courses from the catalog
     */
	public List<Course> viewCourse()
	{
		return adminDaoService.viewCourses();
	}

	/**
     * method for verifying the presence of a course in the catalog
     *
     * @param courseId unique Id to represent a course
     * @return returns true if the course is present in the catalog
     */
	public boolean verifyCourse(String courseId)
	{
		List<Course> courseList=viewCourse();
		for(Course course:courseList)
		{
			if(course.getCourseId().equals(courseId))
				return true;
		}
		return false;
	}
	
	/**
     * method for adding course into the catalog
     *
     * @param newCourse		Course object containing details of the course
     * @return returns status of addCourse operation as a string
     */
	public String addCourse(Course newCourse)
	{
		return adminDaoService.addCourse(newCourse);
	}
	
	/**
     * method for removing course from the catalog
     *
     * @param courseId unique Id to represent a course
     * @return returns status of dropCourse operation as a string
     */
	public String dropCourse(String courseId)
	{
		return adminDaoService.dropCourse(courseId);
	}
	
	/**
     * method for adding a new professor in the system
     *
     * @param newProfessor	Professor object containing details of the professor
     * @return returns status of addProfessor operation as a string
     */
	public String addProfessor(Professor newProfessor)
	{
		return adminDaoService.addProfessor(newProfessor);
	}
	
	/**
     * method for removing professor from the system
     *
     * @param professorId		unique Id to represent a course
     * @return returns status of dropProfessor operation as a string
     */
	public String dropProfessor(String professorId)
	{
		return adminDaoService.dropProfessor(professorId);
	}
	
	/**
     * method for generating grade card and calculating aggregate CGPA of student
     *
     * @param studentId			unique Id to represent a student
     * @param semester			semester for which gradeCard is to be generated
     * @return returns status of generateGradeCard operation as a string
     */
	public String generateGradeCard(String studentId, String semester)
	{
		return adminDaoService.generateGradeCard(studentId, semester);
	}
	
	/**
     * method for getting all admission requests
     *
     * @return List of Students who made Admission Request
     */
	public List<Student> getPendingStudents()
	{
		return adminDaoService.getPendingStudents();
	}
	
	/**
     * method for approving students admission request.
     *
     * @param studentId unique Id for a student
     * @return returns true if the student's request is approved successfully
     */
	public String approveStudent(Student newStudent)
	{
		return adminDaoService.approveStudent(newStudent);
	}

	/**
     * method for getting all the professors
     *
     * @return List of Professors
     */
	@Override
	public List<Professor> viewProfessorList() {
		// TODO Auto-generated method stub
		return adminDaoService.viewProfessorList();
	}
	
}
