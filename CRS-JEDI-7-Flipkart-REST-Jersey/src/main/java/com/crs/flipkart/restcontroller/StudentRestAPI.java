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
	
	@PUT
    @Path("/submitChoices")
    public Response submitChoices() {
        if (UserService.user == null || !UserService.user.getRole().equals("Student")) {
        	return Response.status(500).entity("User not authenticated").build();
        }
        System.out.println(studentService.submittedCourses(UserService.user.getUserId()));
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
        	String status = studentService.makePayment(payment.getStudentEnrollmentId(),payment.getPaymentType(),payment.getAmount());
            return Response.status(201).entity(status).build();
        } catch (Exception e) {
        	return Response.status(500).entity("Something went wrong").build();
        }
    }
	
	
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
