/**
 * 
 */
package com.crs.flipkart.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Raj
 *
 */
public class SemesterRegistration {

	private String studentEnrollmentId;
	private String semester;
	private List<Course> registeredCourses = new ArrayList<Course>();

	/**
	 * @return the studentEnrollmentId
	 */
	public String getStudentEnrollmentId() {
		return studentEnrollmentId;
	}

	/**
	 * @param studentEnrollmentId the studentEnrollmentId to set
	 */
	public void setStudentEnrollmentId(String studentEnrollmentId) {
		this.studentEnrollmentId = studentEnrollmentId;
	}

	/**
	 * @return the semester
	 */
	public String getSemester() {
		return semester;
	}

	/**
	 * @param semester the semester to set
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}

	/**
	 * @return the registeredCourses
	 */
	public List<Course> getRegisteredCourses() {
		return registeredCourses;
	}

	/**
	 * @param registeredCourses the registeredCourses to set
	 */
	public void setRegisteredCourses(List<Course> registeredCourses) {
		this.registeredCourses = registeredCourses;
	}

}
