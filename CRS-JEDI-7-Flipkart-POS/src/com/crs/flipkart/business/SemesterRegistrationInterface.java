package com.crs.flipkart.business;

import java.util.List;

import com.crs.flipkart.bean.Course;

public interface SemesterRegistrationInterface {


	 /**
    * method for getting all the courses from course catalog
    * 
    * @return list of course 
    */
	List<Course> viewCourses();
	
	 /**
     * method for verifying course exists or not in the catalog
     * 
     * @param courseId  unique Id to represent a course
     * @return returns true if course exists in the catalog.
     */

	boolean verifyCourse(String courseId);

	 /**
     * method for adding course for the student 
     *
     * @param studentId  unique Id to represent a student
     * @param courseId  unique Id to represent a course
     * @return returns String which represents the status of adding course 
     */
	String addCourse(String studentId, String courseId);
	
	/**
     * method for dropping course for the student 
     *
     * @param studentId  unique Id to represent a student
     * @param courseId  unique Id to represent a course
     * @return returns String which represents the status of dropping course 
     */
	String dropCourse(String studentId, String courseId);
	
	/**
     * method for getting all opted courses by the student 
     *
     * @param studentId  unique Id to represent a student
     * @return list of courses opted by the student 
     */
	List<Course> viewOptedCourses(String studentId);

	/**
     * method for submitting the course choices of the student 
     *
     * @param studentId  unique Id to represent a student
     * @return returns String which represents the status of submitting course choices 
     */

	String submitOptedCourses(String studentId);

}