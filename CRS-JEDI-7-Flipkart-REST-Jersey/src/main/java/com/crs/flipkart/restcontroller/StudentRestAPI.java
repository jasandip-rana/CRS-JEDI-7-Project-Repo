/**
 * 
 */
package com.crs.flipkart.restcontroller;

import java.util.List;

/**
 * @author Shubham
 *
 */
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.URIReferenceException;

import org.apache.log4j.Logger;

import com.crs.flipkart.bean.*;
import com.crs.flipkart.business.*;


@Path("/student")
public class StudentRestAPI {
	
	private static Logger logger = Logger.getLogger(StudentRestAPI.class);
	StudentInterface studentService = new StudentService();
	SemesterRegistrationInterface semesterRegistrationService = new SemesterRegistrationService();
	
	
	/**
	 * Endpoint for View all courses
	 * 
	 * @return 201,List of courses if student is logged in, otherwise 500
	 */
	@GET
    @Path("/viewCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewCourses() {
        if (UserService.user == null || !UserService.user.getRole().equals("Student")) {
        	return Response.status(500).entity("User not authenticated").build();
        }
        try {
            List<Course> courseList = semesterRegistrationService.viewCourses();
            GenericEntity<List<Course>> entity = new GenericEntity<List<Course>>(courseList) {};
            return Response.ok(entity).build();
        } catch (Exception e) {
        	return Response.status(500).entity("Something went wrong").build();
        }
    }
	
	/**
	 * Endpoint for add course to opted courses list
	 * 
	 * @param course
	 * @return 201, if course is opted successfully, else 500
	 */
	@POST
    @Path("/addCourse")
	@Consumes(MediaType.APPLICATION_JSON)
    public Response addCourse(Course course) {
        if (UserService.user == null || !UserService.user.getRole().equals("Student")) {
        	return Response.status(500).entity("User not authenticated").build();
        }
        if(studentService.submittedCourses(UserService.user.getUserId()))
		{
        	return Response.status(500).entity("You have finalized the courses!").build();
		}
        try {
        	semesterRegistrationService.addCourse(UserService.user.getUserId(),course.getCourseId());
        	return Response.status(201).entity("Course added successfully!").build();
        } catch (Exception e) {
        	return Response.status(500).entity("Error : "+e.getMessage()).build();
        }
    }
	
	/**
	 * Endpoint for drop course from opted courses
	 * 
	 * @param course
	 * @return 201, if course is dropped successfully, else 500 in case of error
	 */
	@DELETE
    @Path("/dropCourse")
	@Consumes(MediaType.APPLICATION_JSON)
    public Response dropCourse(Course course) {
        if (UserService.user == null || !UserService.user.getRole().equals("Student")) {
        	return Response.status(500).entity("User not authenticated").build();
        }
        if(studentService.submittedCourses(UserService.user.getUserId()))
		{
        	return Response.status(500).entity("You have finalized the courses!").build();
		}
        try {
        	String status=semesterRegistrationService.dropCourse(UserService.user.getUserId(),course.getCourseId());
        	return Response.status(201).entity(status).build();
        } catch (Exception e) {
        	return Response.status(500).entity("Error : "+e.getMessage()).build();
        }
    }
	
	/**
	 * Endpoint for view opted courses
	 * 
	 * @return 201, and list of courses if user logged in, else 500 in case of error
	 */
	@GET
    @Path("/viewOptedCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewOptedCourses() {
        if (UserService.user == null || !UserService.user.getRole().equals("Student")) {
        	return Response.status(500).entity("User not authenticated").build();
        }
        if(studentService.submittedCourses(UserService.user.getUserId()))
		{
        	return Response.status(500).entity("You have finalized the courses!").build();
		}
        try {
            List<Course> courseList = semesterRegistrationService.viewOptedCourses(UserService.user.getUserId());
            GenericEntity<List<Course>> entity = new GenericEntity<List<Course>>(courseList) {};
            return Response.ok(entity).build();
        } catch (Exception e) {
        	return Response.status(500).entity("Something went wrong").build();
        }
    }
	
	/**
	 * Endpoint for Submit opted courses
	 * 
	 * @return 201, if courses submitted successfully, else 500 in case of error
	 */
	@PUT
    @Path("/submitChoices")
    public Response submitChoices() {
        if (UserService.user == null || !UserService.user.getRole().equals("Student")) {
        	return Response.status(500).entity("User not authenticated").build();
        }
        if(studentService.submittedCourses(UserService.user.getUserId()))
		{
        	return Response.status(500).entity("You have finalized the courses!").build();
		}
        try {
        	String status = semesterRegistrationService.submitOptedCourses(UserService.user.getUserId());
        	if(!(status.equals("Please select some other courses as some courses are filled and dropped from the list")||status.equals("Please select atleast 4 courses")))
        		return Response.status(201).entity(status).build();
        	return Response.status(500).entity(status).build();
        } catch (Exception e) {
        	return Response.status(500).entity("Something went wrong").build();
        }
    }
	
	/**
	 * Endpoint for view Registered courses
	 * 
	 * @return 201, and list of courses if user logged in, else 500 in case of error
	 */
	@GET
    @Path("/viewRegisteredCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewRegisteredCourses() {
        if (UserService.user == null || !UserService.user.getRole().equals("Student")) {
        	return Response.status(500).entity("User not authenticated").build();
        }
        if(!studentService.submittedCourses(UserService.user.getUserId()))
		{
        	return Response.status(500).entity("You have not finalized the courses!").build();
		}
        try {
            List<Course> courseList = studentService.viewRegisteredCourses(UserService.user.getUserId());
            GenericEntity<List<Course>> entity = new GenericEntity<List<Course>>(courseList) {};
            return Response.ok(entity).build();
        } catch (Exception e) {
        	return Response.status(500).entity("Something went wrong").build();
        }
    }
	
	/**
	 * Endpoint for Make payment
	 * 
	 * @param payment
	 * @return 201, if payment is successful, else 500 in case of error
	 */
	@POST
    @Path("/makePayment")
	@Consumes(MediaType.APPLICATION_JSON)
    public Response makePayment(Payment payment) {
        if (UserService.user == null || !UserService.user.getRole().equals("Student")) {
        	return Response.status(500).entity("User not authenticated").build();
        }
        if(!studentService.submittedCourses(UserService.user.getUserId()))
		{
        	return Response.status(500).entity("You have not finalized the courses!").build();
		}
        if(studentService.getFeeStatus(UserService.user.getUserId()))
		{
        	return Response.status(500).entity("You have already paid the fees!").build();
		}
        try {
        	String status = studentService.makePayment(UserService.user.getUserId(),payment.getPaymentType(),payment.getAmount());
            return Response.status(201).entity(status).build();
        } catch (Exception e) {
        	return Response.status(500).entity("Something went wrong").build();
        }
    }
	
	/**
	 * Endpoint for Student Self Registration
	 * 
	 * @return 201, and gradeCard if user is logged in, else 500 in case of error
	 */
	@GET
    @Path("/gradeCard")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewGradeCard() {
        if (UserService.user == null || !UserService.user.getRole().equals("Student")) {
        	return Response.status(500).entity("User not authenticated").build();
        }
        if(!studentService.getFeeStatus(UserService.user.getUserId()))
		{
        	return Response.status(500).entity("You have not paid the fees!").build();
		}
        try {
        	GradeCard gradeCard = studentService.viewGradeCard(UserService.user.getUserId());
        	GenericEntity<GradeCard> entity = new GenericEntity<GradeCard>(gradeCard) {};
            return Response.ok(entity).build();
        } catch (Exception e) {
        	return Response.status(500).entity("Error : "+e.getMessage()).build();
        }
    }
}
