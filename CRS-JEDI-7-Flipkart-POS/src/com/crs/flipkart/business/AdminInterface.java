/**
 * 
 */
package com.crs.flipkart.business;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;

/**
 * @author jasan
 *
 */
public interface AdminInterface {

	public String addCourse(Course newCourse);
	public String dropCourse(String courseId);
	public String addProfessor(Professor newProfessor);
	public String dropProfessor(String professorId);
	public String generateGradeCard(String studentId);
	public String approveStudent(Student newStudent);
	
}
