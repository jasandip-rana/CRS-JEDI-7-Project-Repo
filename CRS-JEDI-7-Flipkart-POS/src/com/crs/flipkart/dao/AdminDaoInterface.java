package com.crs.flipkart.dao;

import java.sql.SQLException;
import java.util.List;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.exceptions.*;


public interface AdminDaoInterface {
	
	/**
     * method for getting all courses in the database
     *
     * @return returns List of all courses in the database
     */
	public List<Course> viewCourses();

	/**
     * method for adding course into database
     *
     * @param newCourse		Course object containing details of the course
     * @return returns status of addCourse operation as a string
     */
	String addCourse(Course newCourse);

	/**
     * method for removing course from the database
     *
     * @param courseId unique Id to represent a course
     * @return returns status of dropCourse operation as a string
     */
	public void dropCourse(String courseId) throws CourseNotFoundException;
	
	/**
     * method for getting all Pending admission requests
     *
     * @return List of students with pending approval request
     */
	List<Student> getPendingStudents();

	/**
     * method to approve a student by student id
     *
     * @param newStudent	Student object contains details of student to be approved
     * @return returns status of approveStudent operation as a string
     */
	String approveStudent(Student newStudent);

	/**
     * method for getting all the professors
     *
     * @return List of Professors
     */
	List<Professor> viewProfessorList();
	
	/**
     * method for adding professor into database
     *
     * @param newProfessor	Professor object containing details of the professor
     * @return returns status of addProfessor operation as a string
     */
	String addProfessor(Professor newProfessor) throws EmailAlreadyInUseException;
	
	/**
     * method for removing professor from the database
     *
     * @param professorId		unique Id to represent a course
     * @return returns status of dropProfessor operation as a string
     */
	public void dropProfessor(String professorId) throws UserNotFoundException;

	/**
     * method for getting all students whose grade card is not yet generated
     *
     * @return List of students with pending grade card generation
     */
	public List<Student> getPendingGradeStudents();
	
	/**
     * method for generating grade card and calculating aggregate CGPA of student
     *
     * @param studentId			unique Id to represent a student
     * @param semester			semester for which gradeCard is to be generated
     * @return returns status of generateGradeCard operation as a string
     */
	public void generateGradeCard(String studentId, String semester) throws GradeNotAllotedException;

}