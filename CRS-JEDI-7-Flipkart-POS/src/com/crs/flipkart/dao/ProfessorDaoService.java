/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.crs.flipkart.bean.*;
import com.crs.flipkart.constants.SQLQueries;
import com.crs.flipkart.utils.dbUtil;

/**
 * @author Shubham
 * Implementation of Professor DAO services utilized by business services
 */
public class ProfessorDaoService implements ProfessorDaoInterface {

	public static Connection conn = dbUtil.getConnection();
	
	/**
	 * Method to obtain a list of courses using SQL commands
	 * 
	 * @return returns the courses present in the database
	 */
	@Override
	public List<Course> viewCourses() {
        try {
            PreparedStatement ps = conn.prepareStatement(SQLQueries.FETCH_COURSES);
            ResultSet rs = ps.executeQuery();
            List<Course> courses = new ArrayList<Course>();
            while (rs.next()) {
                Course c = new Course();
                c.setCourseId(rs.getString("courseId"));
                c.setCourseName(rs.getString("courseName"));
                c.setCourseFee(rs.getFloat("courseFee"));
                c.setDepartmentName(rs.getString("department"));
                c.setProfessorId(rs.getString("professorId"));
                c.setStudentCount(rs.getInt("studentCount"));
                courses.add(c);
            }
            return courses;
        } catch (SQLException e) {
        	e.printStackTrace();
        	return null;
        }
    }
	
	/**
	 * Method to check if the course is already alloted or not, and then to allot the course using SQL commands
	 * 
	 * @param professorId unique Id to represent a professor
	 * @param courseId unique Id to represent a course
	 * @return returns a string that indicates if the course is successfully alloted in the database
	 */
	@Override
	public String selectCourse(String professorId, String courseId) {
        try {
        	PreparedStatement psCheck = conn.prepareStatement(SQLQueries.CHECK_VACANT_COURSE);
        	psCheck.setString(1, courseId);
        	ResultSet rs = psCheck.executeQuery();
        	if(!rs.next())
        	{	
        		return "Course already alloted.";
        	}
        	else
        	{
        		if(rs.getString("professorId") != null)
					return "Course already alloted";
        	}
        	
        	
            PreparedStatement ps = conn.prepareStatement(SQLQueries.SELECT_COURSE_FOR_PROF);
            ps.setString(1, professorId);
            ps.setString(2, courseId);
            
            if(ps.executeUpdate() == 1)
            	return "Course successfully alloted.";
            
 
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return "Course selection failed.";
    }

	/**
	 * Method for adding the grades of a student for a course in a semester using SQL commands
	 * 
	 * @param studentId unique Id to represent a student
	 * @param courseId unique Id to represent a course
	 * @param grade grade point provided for the student enrolled in the course taught by the professor
	 * @param semester indicates the semester
	 * @return returns a string indicating the if the grade was successfully added in the database
	 */
	@Override
	public String addGrade(String studentId, String courseId, float grade, String semester) {
        try {
            PreparedStatement ps = conn.prepareStatement(SQLQueries.ADD_GRADE);
            ps.setString(1, studentId);
            ps.setString(2, courseId);
            ps.setFloat(3, grade);
            ps.setString(4, semester);
            int rowAffected = ps.executeUpdate();
            if (rowAffected == 1)
            	return "Student successfully graded.";
            
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return "Student grading failed.";
    }
	
	/**
	 * Method for retrieving the students enrolled in a course using SQL commands
	 * 
	 * @param courseId unique Id to represent a course
	 * @return returns a list of strings indicating the students enrolled in a course from the database
	 */
	@Override
	public List<String> viewEnrolledStudents(String courseId)
	{
		try {
			List<String> enrolledStudents = new ArrayList<String>();
            PreparedStatement ps = conn.prepareStatement(SQLQueries.VIEW_ENROLLED_STUDENTS);
            ps.setString(1, courseId);
            ResultSet rs = ps.executeQuery(); 
            if(rs == null) {
            	return null;
            }
            while(rs.next())
            {
            	enrolledStudents.add(rs.getString("student.studentId") + " " + rs.getString("student.name"));
            }
            
            return enrolledStudents;
            
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return null;
	}
	
}
