/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.constants.SQLQueries;
import com.crs.flipkart.utils.dbUtil;

/**
 * @author Shubham
 *
 */
public class SemesterRegistrationDaoService implements SemesterRegistrationDaoInterface {

	CatalogDaoInterface catalogService = new CatalogDaoService();

	public static Connection conn = dbUtil.getConnection();
	
	@Override
	public String addCourse(String studentId, String courseId) {
		// TODO Auto-generated method stub
		try {
            PreparedStatement ps = conn.prepareStatement(SQLQueries.ADD_OPTED_COURSE);
            ps.setString(1, studentId);
            ps.setInt(2, 0);
            ps.setString(3, courseId);
            int rs=ps.executeUpdate();
            if(rs==1)
            	return "Successfully added";
        } catch (SQLException e) {
        	e.printStackTrace();
        	return "Database Error";
        }
		return "Database Error";
	}

	@Override
	public List<Course> viewOptedCourses(String studentId) {
		List<Course> courseList = new ArrayList<Course>();

		try {
			PreparedStatement ps = conn.prepareStatement(SQLQueries.FETCH_OPTED_COURSES);
			ps.setString(1, studentId);

			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				String courseId = rs.getString("courseId");
				String courseName = rs.getString("courseName");
				float courseFee = rs.getFloat("courseFee");
				String department = rs.getString("department");
				String professorId = rs.getString("professorId");
				int studentCount = rs.getInt("studentCount");
				Course course = new Course(courseId,courseName,courseFee,department,professorId,studentCount);
				courseList.add(course);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return courseList;

	}

	@Override
	public String dropCourse(String studentId, String courseId) {
		// TODO Auto-generated method stub
		try {
            PreparedStatement ps = conn.prepareStatement(SQLQueries.DROP_OPTED_COURSE);
            ps.setString(1, studentId);
            ps.setString(2, courseId);
            int rs=ps.executeUpdate();
            if(rs==1)
            	return "Successfully dropped course";
        } catch (SQLException e) {
        	e.printStackTrace();
        	return "Database Error";
        }
		return "Database Error";
	}

	public boolean increaseStudentCount(String studentId) {
		// TODO Auto-generated method stub
		try {
			List<Course> optedCourses = viewOptedCourses(studentId);
            for(int i=0;i<4;i++)
            {
            	PreparedStatement ps = conn.prepareStatement(SQLQueries.INCREMENT_STUDENT_COUNT);
            	ps.setInt(1, optedCourses.get(i).getStudentCount()+1);
            	ps.setString(2, optedCourses.get(i).getCourseId());
            	int rs=ps.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
        	e.printStackTrace();
        	return false;
        }
	}
	
	@Override
	public String submitOptedCourses(String studentId) {
		// TODO Auto-generated method stub
		try {
            PreparedStatement ps = conn.prepareStatement(SQLQueries.SUBMIT_OPTED_COURSES);
            ps.setString(1, studentId);
            int rs=ps.executeUpdate();
            if(rs==4)
            {
            	increaseStudentCount(studentId);
            	return "Successfully submitted courses";
            }
        } catch (SQLException e) {
        	e.printStackTrace();
        	return "Database Error";
        }
		return "Database Error";
	}

}
