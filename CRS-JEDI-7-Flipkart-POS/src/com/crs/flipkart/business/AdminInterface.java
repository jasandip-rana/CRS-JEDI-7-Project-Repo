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

	public List<Course> viewCourse();
	public String addCourse(Course newCourse);
	public String dropCourse(String courseId);
	public List<Student> getPendingStudents();
	public String approveStudent(Student newStudent);
	public List<Professor> viewProfessorList();
	public String addProfessor(Professor newProfessor);
	public String dropProfessor(String professorId);
	public String generateGradeCard(String studentId,String semester);
	
}
