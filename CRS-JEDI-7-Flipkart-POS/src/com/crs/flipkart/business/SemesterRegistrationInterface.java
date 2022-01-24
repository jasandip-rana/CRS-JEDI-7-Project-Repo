/**
 * 
 */
package com.crs.flipkart.business;

import java.util.List;

import com.crs.flipkart.bean.Course;

/**
 * @author jasan
 *
 */
public interface SemesterRegistrationInterface {

	public List<Course> viewCourses();
	public String addCourse(String studentID, String courseID);
	public String dropCourse(String studentID, String courseID);
	public List<Course> viewOptedCourses(String studentID);
	public String submitChoice(String studentID);
}
