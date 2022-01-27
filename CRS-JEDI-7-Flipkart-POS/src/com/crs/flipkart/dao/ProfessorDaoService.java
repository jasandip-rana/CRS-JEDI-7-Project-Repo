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
 *
 */
public class ProfessorDaoService implements ProfessorDaoInterface {

	public static Connection conn = dbUtil.getConnection();
	
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
	
	@Override
	public List<String> viewEnrolledStudents(String courseId)
	{
		try {
			List<String> enrolledStudents = new ArrayList<String>();
            PreparedStatement ps = conn.prepareStatement(SQLQueries.VIEW_ENROLLED_STUDENTS);
            ps.setString(1, courseId);
            ResultSet rs = ps.executeQuery(); 
            while(rs.next())
            {
            	enrolledStudents.add(rs.getString("user.userId") + " " + rs.getString("user.name"));
            }
            
            int rowAffected = ps.executeUpdate();
            if (rowAffected == 1)
            	return enrolledStudents;
            
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return null;
	}
	
}
