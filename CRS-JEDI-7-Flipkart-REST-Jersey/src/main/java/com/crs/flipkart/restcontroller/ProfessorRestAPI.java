/**
 * 
 */
package com.crs.flipkart.restcontroller;

import java.util.*;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.*;
import com.crs.flipkart.business.*;

/**
 * @author Shubham
 *
 */
@Path("/professor")
public class ProfessorRestAPI {

	private static Logger logger = Logger.getLogger(ProfessorRestAPI.class);
	ProfessorInterface professorService = new ProfessorService();
	
	/**
	 * Endpoint for View available courses for professor
	 * 
	 * @return 201, list of courses if user is logged in, else 500 in case of error
	 */
	@GET
    @Path("/viewAvailableCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewAvailableCourses() {
        if (UserService.user == null || !UserService.user.getRole().equals("Professor")) {
        	return Response.status(500).entity("User not authenticated").build();
        }
        try {
            List<Course> courseList = professorService.viewCourses();
            GenericEntity<List<Course>> entity = new GenericEntity<List<Course>>(courseList) {};
            return Response.ok(entity).build();
        } catch (Exception e) {
        	return Response.status(500).entity("Something went wrong").build();
        }
    }
	
	/**
	 * Endpoint for View selected courses for professor
	 * 
	 * @return 201, list of courses if user is logged in, else 500 in case of error
	 */
	@GET
    @Path("/viewSelectedCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewSelectedCourses() {
        if (UserService.user == null || !UserService.user.getRole().equals("Professor")) {
        	return Response.status(500).entity("User not authenticated").build();
        }
        try {
            List<Course> courseList = professorService.viewSelectedCourses(UserService.user.getUserId());
            GenericEntity<List<Course>> entity = new GenericEntity<List<Course>>(courseList) {};
            return Response.ok(entity).build();
        } catch (Exception e) {
        	return Response.status(500).entity("Something went wrong").build();
        }
    }
	
	/**
	 * Endpoint for Indicating courses
	 * 
	 * @param course
	 * @return 201, if course successfully selected by professor, else 500 in case of error
	 */
	@PUT
	@Path("/selectCourse")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response selectCourse(Course course)
	{
		if (UserService.user == null || !UserService.user.getRole().equals("Professor")) {
        	return Response.status(500).entity("User not authenticated").build();
        }
		try {			
			professorService.indicateCourse(UserService.user.getUserId(), course.getCourseId());
			return Response.status(201).entity("Course selected").build();
		}
		catch(Exception e) {
			return Response.status(500).entity("Error : " + e.getMessage()).build();
		}
		
	}
	
	/**
	 * Endpoint for View enrolled students in a course
	 * 
	 * @param courseId
	 * @return 201, list of students if user is logged in and teaches the given course, else 500 in case of error
	 */
	@GET
    @Path("/viewEnrolledStudents/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewEnrolledStudents(
    		@NotNull
			@PathParam("courseId") String courseId) {
        if (UserService.user == null || !UserService.user.getRole().equals("Professor")) {
        	return Response.status(500).entity("User not authenticated").build();
        }
        try {
        	if(!professorService.validateCourse(courseId, UserService.user.getUserId()))
    		{
            	return Response.status(500).entity("You do not teach this code").build();
    		}
    		List<Student>enrolledStudents=professorService.viewEnrolledStudents(courseId);
            GenericEntity<List<Student>> entity = new GenericEntity<List<Student>>(enrolledStudents) {};
            return Response.ok(entity).build();
        } catch (Exception e) {
        	return Response.status(500).entity("Something went wrong").build();
        }
    }

	/**
	 * Endpoint for Grading the student
	 * 
	 * @param grade
	 * @return 201, if grade successfully added, else 500 in case of error
	 */
	@POST
	@Path("/gradeStudent")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response gradeStudent(Grade grade)
	{
		if (UserService.user == null || !UserService.user.getRole().equals("Professor")) {
        	return Response.status(500).entity("User not authenticated").build();
        }
		try {	
			if(!professorService.validateCourse(grade.getCourseId(), UserService.user.getUserId()))
    		{
            	return Response.status(500).entity("You do not teach this course").build();
    		}
			List<Student> enrolledStudents = professorService.viewEnrolledStudents(grade.getCourseId());
			boolean flag=false;
			for(Student student:enrolledStudents)
			{
				System.out.println(student.getStudentEnrollmentId());
				if(student.getStudentEnrollmentId().equals(grade.getStudentEnrollmentId()))
				{
					flag=true;
					break;
				}
			}
			if(!flag)
			{
            	return Response.status(500).entity("Student have not enrolled in this course").build();
			}
			List<Student> ungradedStudents = professorService.viewUngradedStudents(grade.getCourseId());
			for(Student student:ungradedStudents)
			{
				System.out.println(student.getStudentEnrollmentId());
				if(student.getStudentEnrollmentId().equals(grade.getStudentEnrollmentId()))
				{
					professorService.gradeStudent(grade.getStudentEnrollmentId(), grade.getCourseId(), grade.getStudentGrade(), grade.getSemester());
					return Response.status(201).entity("Student has been graded").build();
				}
			}
			return Response.status(500).entity("This student has been graded").build();
		}
		catch(Exception e) {
			return Response.status(500).entity("Error : " + e.getMessage()).build();
		}
		
	}
	
}
