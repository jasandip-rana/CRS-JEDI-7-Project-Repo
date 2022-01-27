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
import com.crs.flipkart.business.UserInterface;
import com.crs.flipkart.constants.SQLQueries;
import com.crs.flipkart.utils.dbUtil;

/**
 * @author Shubham
 *
 */
public class AdminDaoService implements AdminDaoInterface {
	
	public static Connection conn = dbUtil.getConnection();
	UserDaoInterface userDaoService = new UserDaoService();
	
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
	
	public List<Student> getPendingStudents()
	{
		List<Student> studentList= new ArrayList<Student>();
		 try {
	            PreparedStatement ps = conn.prepareStatement(SQLQueries.GET_PENDING_STUDENTS);
	      
	            ResultSet rs = ps.executeQuery(); 
	            while(rs.next())
	            {
	            	Student student = new Student();
	            	student.setStudentEnrollmentId(rs.getString("studentId"));
	            	student.setName(rs.getString("name"));
	            	studentList.add(student);
	            }
	            return studentList;
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
		return null;
	}
	
	@Override
	public String approveStudent(Student newStudent) {
        try {
            PreparedStatement ps = conn.prepareStatement(SQLQueries.APPROVE_ADDMISSION_REQUEST);
            ps.setString(1, newStudent.getStudentEnrollmentId());
            if(ps.executeUpdate() == 1)
            	return "Student approved successfully.";

        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return "Student not approved.";
    }
	
	   @Override
	public List<Professor> viewProfessorList() {
		   try {
				List<Professor> professorList = new ArrayList<Professor>();
	            PreparedStatement ps = conn.prepareStatement(SQLQueries.LIST_PROFESSORS);
	            ResultSet rs = ps.executeQuery(); 
	            while(rs.next())
	            {
	            	//professorList.add(rs.getString("professor.professorID") + " " + rs.getString("user.name")+" "+rs.getString("user.email")
	            	//+" "+rs.getString("professor.department"));
	            	Professor professor = new Professor();
	            	professor.setProfessorId(rs.getString("professorId"));
	            	professor.setName(rs.getString("name"));
	            	professor.setDepartment(rs.getString("department"));
	            	professorList.add(professor);
	            }
	            
	            return professorList;
	            
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	        return null;
		}

	   public String addProfessor(Professor newProfessor) {
	        try {
	    		String id = userDaoService.createUser(newProfessor.getName(), newProfessor.getEmail(), newProfessor.getPassword(), "Professor");
	            PreparedStatement ps = conn.prepareStatement(SQLQueries.ADD_PROFESSOR);
	            ps.setString(1, id);
	            ps.setString(2, newProfessor.getName());
	            ps.setString(3, newProfessor.getContactNumber());
	            ps.setFloat(4, newProfessor.getSalary());
	            ps.setString(5, newProfessor.getDepartment());
	            ps.setString(6, newProfessor.getDoj());
	            

	            if(ps.executeUpdate() == 1)
	            	return "Professor added successfully. Professor id : "+id;

	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	        return "Professor not added.";
	    }
	   public String dropProfessor(String professorId) {
	        try {
	            PreparedStatement ps = conn.prepareStatement(SQLQueries.DROP_PROFESSOR);
	            ps.setString(1, professorId);
	            ps.setString(2, professorId);
	            if(ps.executeUpdate()!=0)
	            	return "Professor dropped successfully.";

	        } catch (SQLException e) {
	        	e.printStackTrace();
	        }
	        return "Professor not dropped.";
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
	            if(countCourses!=4)
	            	return "GradeCard generation unsuccessfull.";
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
