/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.*;
import com.crs.flipkart.business.UserInterface;
import com.crs.flipkart.constants.SQLQueries;
import com.crs.flipkart.exceptions.CourseNotFoundException;
import com.crs.flipkart.exceptions.EmailAlreadyInUseException;
import com.crs.flipkart.exceptions.GradeNotAllotedException;
import com.crs.flipkart.exceptions.UserNotFoundException;
import com.crs.flipkart.utils.dbUtil;

/**
 * @author Shubham
 * Implementation of admin dao interface
 */
public class AdminDaoService implements AdminDaoInterface {
	
	private static Logger logger = Logger.getLogger(AdminDaoService.class);
	public static Connection conn = dbUtil.getConnection();
	UserDaoInterface userDaoService = new UserDaoService();
	
	/**
     * method for getting all courses in the database
     *
     * @return returns List of all courses in the database
     */
	@Override
	public List<Course> viewCourses(){
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
        	logger.debug("Error: " + e.getMessage());
        	return null;
        }
    }
	
	/**
     * method for adding course into database
     *
     * @param newCourse		Course object containing details of the course
     * @return returns status of addCourse operation as a string
     */
	@Override
	public String addCourse(Course newCourse){
        try {
            PreparedStatement ps = conn.prepareStatement(SQLQueries.ADD_COURSE);
            ps.setString(1, newCourse.getCourseId());
            ps.setString(2, newCourse.getCourseName());
            ps.setFloat(3, newCourse.getCourseFee());
            ps.setString(4, newCourse.getDepartmentName());

            if(ps.executeUpdate() == 1)
            	return "Course added successfully.";

        } catch (SQLException e) {
        	logger.debug("Error: " + e.getMessage());
        }
        return "Course not added.";
    }
	
	/**
     * method for removing course from the database
     *
     * @param courseId unique Id to represent a course
     * @return returns status of dropCourse operation as a string
     */
	@Override
	public void dropCourse(String courseId) throws CourseNotFoundException{
        try {
            PreparedStatement ps = conn.prepareStatement(SQLQueries.DROP_COURSE);
            ps.setString(1, courseId);
            if(ps.executeUpdate() == 1)
            	logger.info("Course dropped successfully.\n");
            else
            	throw new CourseNotFoundException(courseId);

        } catch (SQLException e) {
        	logger.error("Error: " + e.getMessage());
        }
        
    }
	
	/**
     * method for getting all Pending admission requests
     *
     * @return List of students with pending approval request
     */
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
	        	logger.debug("Error: " + e.getMessage());
	        }
		return null;
	}
	
	/**
     * method to approve a student by student id
     *
     * @param newStudent	Student object contains details of student to be approved
     * @return returns status of approveStudent operation as a string
     */
	@Override
	public String approveStudent(Student newStudent) {
        try {
            PreparedStatement ps = conn.prepareStatement(SQLQueries.APPROVE_ADDMISSION_REQUEST);
            ps.setString(1, newStudent.getStudentEnrollmentId());
            ps.executeUpdate();
            ps = conn.prepareStatement(SQLQueries.INSERT_REGISTRATION);
            ps.setString(1, newStudent.getStudentEnrollmentId());
            ps.setString(2, "1");
            ps.executeUpdate();
            return "Student approved";

        } catch (SQLException e) {
        	logger.debug("Error: " + e.getMessage());
        }
        return "Student not approved.";
    }
	
	
	/**
     * method for getting all the professors
     *
     * @return List of Professors
     */
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
	        	logger.debug("Error: " + e.getMessage());
	        }
	        return null;
		}

	  /**
	     * method for adding professor into database
	     *
	     * @param newProfessor	Professor object containing details of the professor
	     * @return returns status of addProfessor operation as a string
	     */
	   public String addProfessor(Professor newProfessor) throws EmailAlreadyInUseException{
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
	        	logger.debug("Error: " + e.getMessage());
	        } catch(EmailAlreadyInUseException e) {
	        	throw e;
	        }
	        return "Professor not added.";
	    }
	   
	   /**
	     * method for removing professor from the database
	     *
	     * @param professorId		unique Id to represent a course
	     * @return returns status of dropProfessor operation as a string
	     */
	   public void dropProfessor(String professorId) throws UserNotFoundException{
	        try {
	            PreparedStatement ps = conn.prepareStatement(SQLQueries.REMOVE_USER);
	            ps.setString(1, professorId);
	            if(ps.executeUpdate()!=0)
	            {
	            	ps = conn.prepareStatement(SQLQueries.DROP_PROFESSOR);
	            	ps.setString(1, professorId);
	            	if(ps.executeUpdate()!=0)
	            		return;
	            }
	            else {
	            	throw new UserNotFoundException(professorId);
	            }
	        } catch (SQLException e) {
	        	logger.error("Error: " + e.getMessage());
	        }
	    }

	   /**
	     * method for generating grade card and calculating aggregate CGPA of student
	     *
	     * @param studentId			unique Id to represent a student
	     * @param semester			semester for which gradeCard is to be generated
	     * @return returns status of dropProfessor operation as a string
	     */
	   @Override
	public void generateGradeCard(String studentId, String semester) throws GradeNotAllotedException
	   {
		   try {
	            PreparedStatement ps = conn.prepareStatement(SQLQueries.GET_GRADES);
	            ps.setString(1, studentId);
	            ps.setString(2, semester);
	            ResultSet rs = ps.executeQuery();
	            float total = 0;
	            int countCourses = 0;
	            while (rs.next()) {
	                countCourses++;
	                total += rs.getFloat("grade.gpa");
	            }
	            if(countCourses!=4)
	            	throw new GradeNotAllotedException(studentId);
	            total = total/(float)countCourses;
	            
	            PreparedStatement psUpdate = conn.prepareStatement(SQLQueries.GENERATE_GRADES);
	            psUpdate.setString(1, studentId);
	            psUpdate.setString(2, semester);
	            psUpdate.setFloat(3, total);
	            
	            if(psUpdate.executeUpdate() == 1)
	            	return;
	            
	        } catch (SQLException e) {
	        	logger.debug("Error: " + e.getMessage());
	        }
	   }
}
