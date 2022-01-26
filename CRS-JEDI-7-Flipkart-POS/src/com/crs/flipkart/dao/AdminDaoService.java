/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.crs.flipkart.bean.*;
import com.crs.flipkart.constants.SQLQueries;
import com.crs.flipkart.utils.dbUtil;

/**
 * @author Shubham
 *
 */
public class AdminDaoService implements AdminDaoInterface {
	
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
	public String addCourse(Course newCourse) {
        try {
            PreparedStatement ps = conn.prepareStatement(SQLQueries.ADD_COURSE);
            ps.setString(1, newCourse.getCourseId());
            ps.setString(2, newCourse.getCourseName());
            ps.setFloat(3, newCourse.getCourseFee());
            ps.setString(4, newCourse.getDepartmentName());

            if(ps.executeUpdate() == 1)
            	return "Course added successfully.";

        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return "Course not added.";
    }
	
	@Override
	public String dropCourse(String courseId) {
        try {
            PreparedStatement ps = conn.prepareStatement(SQLQueries.DROP_COURSE);
            ps.setString(1, courseId);
            if(ps.executeUpdate() == 1)
            	return "Course dropped successfully.";

        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return "Course not dropped.";
    }
	
	@Override
	public String approveStudent(Student newStudent) {
        try {
            PreparedStatement ps = conn.prepareStatement(SQLQueries.APPROVE_ADDMISSION_REQUEST);
            ps.setString(1, newStudent.getUserId());
            if(ps.executeUpdate() == 1)
            	return "Student approved successfully.";

        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return "Student not approved.";
    }
	
	   @Override
	public List<Professor> viewProfessorList() {
//	        try {
//	            PreparedStatement ps = conn.prepareStatement(SQLQueries.VIEW_PROFESSOR);
//	            ResultSet rs = ps.executeQuery();
//	            List<Professor> professors = new ArrayList<Professor>();
//	            while (rs.next()) {
//	                Professor p = new Professor();
//	                p.setProfessorId(rs.getInt("id"));
//	                p.setUserName(rs.getString("name"));
//	                p.setUserEmailId(rs.getString("email"));
//	                p.setDepartment(rs.getString("department"));
//	                p.setDesignation(rs.getString("designation"));
//	                professors.add(p);
//	            }
//	            return professors;
//	        } catch (SQLException e) {
//	            logger.info("Error: " + e.getMessage());
//	        }
	        return null;
//	    }
	}

	   @Override
	public String generateGradeCard(String studentId, String semester)
	   {
		   try {
	            PreparedStatement ps = conn.prepareStatement(SQLQueries.GET_GRADES);
	            ResultSet rs = ps.executeQuery();
	            float total = 0;
	            int countCourses = 0;
	            while (rs.next()) {
	                countCourses++;
	                total += rs.getFloat("grade.gpa");
	            }
	            total = total/(float)countCourses;
	            
	            PreparedStatement psUpdate = conn.prepareStatement(SQLQueries.GENERATE_GRADES);
	            psUpdate.setString(1, studentId);
	            psUpdate.setString(2, semester);
	            psUpdate.setFloat(3, total);
	            
	            if(ps.executeUpdate() == 1)
	            	return "GradeCard generated successfully.";
	            
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
		   
		   return "GradeCard generation unsuccessfull.";
	   }
}
