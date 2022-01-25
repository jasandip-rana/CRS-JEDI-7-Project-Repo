/**
 * 
 */
package com.crs.flipkart.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.utils.*;
import com.crs.flipkart.constants.*;

/**
 * @author Shubham
 *
 */
public class CatalogDaoService implements CatalogDaoInterface {

	public static Connection conn = dbUtil.getConnection();
	List<Course> courseList = new ArrayList<Course>();

	@Override
	public List<Course> viewCourses() {
		try {

			PreparedStatement ps = conn.prepareStatement(SQLQueries.FETCH_COURSES);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
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
		}
		return courseList;
	}
}
