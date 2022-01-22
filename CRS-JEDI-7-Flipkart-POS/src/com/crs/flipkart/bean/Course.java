/**
 * 
 */
package com.crs.flipkart.bean;

import java.util.*;

/**
 * @author Raj
 *
 */
public class Course {

	private String courseId;
	private String courseName;
	private String professorId;
	private List<String> registeredStudents = new ArrayList<String>();

	/**
	 * @return the courseId
	 */
	public String getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * @return the professorId
	 */
	public String getProfessorId() {
		return professorId;
	}

	/**
	 * @param professorId the professorId to set
	 */
	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}

	/**
	 * @return the registeredStudents
	 */
	public List<String> getRegisteredStudents() {
		return registeredStudents;
	}

	/**
	 * @param registeredStudents the registeredStudents to set
	 */
	public void setRegisteredStudents(List<String> registeredStudents) {
		this.registeredStudents = registeredStudents;
	}

}
