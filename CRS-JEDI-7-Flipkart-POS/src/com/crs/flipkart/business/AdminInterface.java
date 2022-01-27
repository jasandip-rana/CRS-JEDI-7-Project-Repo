/**
 * 
 */
package com.crs.flipkart.business;

import java.util.List;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;

/**
 * @author jasan
 *
 */
public interface AdminInterface {

	/**
     * method for viewing all courses in the catalog
     *
     * @return returns List of all courses from the catalog
     */
	public List<Course> viewCourse();
	
	/**
     * method for adding course into the catalog
     *
     * @param newCourse		Course object containing details of the course
     * @return returns status of addCourse operation as a string
     */
	public String addCourse(Course newCourse);
	
	/**
     * method for verifying the presence of a course in the catalog
     *
     * @param courseId unique Id to represent a course
     * @return returns true if the course is present in the catalog
     */
	public boolean verifyCourse(String courseId);
	
	/**
     * method for removing course from the catalog
     *
     * @param courseId unique Id to represent a course
     * @return returns status of dropCourse operation as a string
     */
	public String dropCourse(String courseId);
	
	/**
     * method for getting all admission requests
     *
     * @return List of Students who made Admission Request
     */
	public List<Student> getPendingStudents();
	
	/**
     * method for approving students admission request.
     *
     * @param studentId unique Id for a student
     * @return returns true if the student's request is approved successfully
     */
	public String approveStudent(Student newStudent);
	
	/**
     * method for getting all the professors
     *
     * @return List of Professors
     */
	public List<Professor> viewProfessorList();
	
	/**
     * method for adding a new professor in the system
     *
     * @param newProfessor	Professor object containing details of the professor
     * @return returns status of addProfessor operation as a string
     */
	public String addProfessor(Professor newProfessor);
	
	/**
     * method for removing professor from the system
     *
     * @param professorId		unique Id to represent a course
     * @return returns status of dropProfessor operation as a string
     */
	public String dropProfessor(String professorId);
	
	/**
     * method for generating grade card and calculating aggregate CGPA of student
     *
     * @param studentId			unique Id to represent a student
     * @param semester			semester for which gradeCard is to be generated
     * @return returns status of generateGradeCard operation as a string
     */
	public String generateGradeCard(String studentId,String semester);
	
}
