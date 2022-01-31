/**
 * 
 */
package com.crs.flipkart.restcontroller;

import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.crs.flipkart.bean.*;
import com.crs.flipkart.business.*;
/**
 * @author Shubham
 *
 */
@Path("/administrator")
public class AdminRestAPI {

	AdminInterface adminService = new AdminService();
	
	@GET
    @Path("/viewCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewCourses() {
		
        if (UserService.user == null || !UserService.user.getRole().equals("Admin")) {
        	return Response.status(500).entity("User not authenticated").build();
        }
        
        List<Course> courseList = adminService.viewCourse();
        GenericEntity<List<Course>> entity = new GenericEntity<List<Course>>(courseList) {};
        return Response.ok(entity).build();
    }
	
	@POST
	@Path("/addCourse")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCourse(Course course)
	{
		if (UserService.user == null || !UserService.user.getRole().equals("Admin")) {
        	return Response.status(500).entity("User not authenticated").build();
        }
		try {	
			adminService.verifyCourse(course.getCourseId());
			adminService.addCourse(course);
			return Response.status(200).entity("Course successfully added").build();
		}
		catch(Exception e) {
			return Response.status(500).entity("Error : " + e.getMessage()).build();
		}
	}
	
	@DELETE
	@Path("/dropCourse")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response dropCourse(Course course)
	{
		if (UserService.user == null || !UserService.user.getRole().equals("Admin")) {
        	return Response.status(500).entity("User not authenticated").build();
        }
		
		try {
			adminService.dropCourse(course.getCourseId());
			return Response.status(200).entity("Course dropped successfully").build();
		}
		catch(Exception e)
		{
			return Response.status(500).entity("Error : " + e.getMessage()).build();
		}
	}
	
	@PUT
	@Path("/approveStudent")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response approveStudent(Student student)
	{
		if (UserService.user == null || !UserService.user.getRole().equals("Admin")) {
        	return Response.status(500).entity("User not authenticated").build();
        }
		
		List<Student> studentList = adminService.getPendingStudents();
		
		for(Student s : studentList)
		{
			if(s.getStudentEnrollmentId().equals(student.getStudentEnrollmentId()))
			{
				adminService.approveStudent(student);
				return Response.status(200).entity("Student approved successfully").build();
			}
		}
		
		return Response.status(500).entity("Student not approved").build();
	}
	
	@POST
	@Path("/addProfessor")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProfessor(Professor professor)
	{
		if (UserService.user == null || !UserService.user.getRole().equals("Admin")) {
        	return Response.status(500).entity("User not authenticated").build();
        }
		try {	
			adminService.addProfessor(professor);
			return Response.status(200).entity("Professor successfully added").build();
		}
		catch(Exception e) {
			return Response.status(500).entity("Error : " + e.getMessage()).build();
		}
	}
	
	@DELETE
	@Path("/dropProfessor")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response dropProfessor(Professor professor)
	{
		if (UserService.user == null || !UserService.user.getRole().equals("Admin")) {
        	return Response.status(500).entity("User not authenticated").build();
        }

		try {
			adminService.dropProfessor(professor.getProfessorId());
			return Response.status(200).entity("Professor dropped successfully").build();
		}
		catch(Exception e)
		{
			return Response.status(500).entity("Error : " + e.getMessage()).build();
		}
	}
	
	@GET
	@Path("/viewProfessorList")
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewProfessorList()
	{
		if (UserService.user == null || !UserService.user.getRole().equals("Admin")) {
        	return Response.status(500).entity("User not authenticated").build();
        }
		
		List<Professor> professorList = adminService.viewProfessorList();
        GenericEntity<List<Professor>> entity = new GenericEntity<List<Professor>>(professorList) {};
        return Response.ok(entity).build();
	}
	
	
	@POST
	@Path("/generateGradeCard")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response generateGradeCard(Student student)
	{
		if (UserService.user == null || !UserService.user.getRole().equals("Admin")) {
        	return Response.status(500).entity("User not authenticated").build();
        }

		List<Student> pendingStudents = adminService.getPendingGradeStudents();
		
		for(Student pendingStudent : pendingStudents)
		{
			if(pendingStudent.getStudentEnrollmentId().equals(student.getStudentEnrollmentId()))
			{
				try {
					adminService.generateGradeCard(student.getStudentEnrollmentId(), "1");
					return Response.status(200).entity("GradeCard generated successfully").build();
				}
				catch(Exception e)
				{
					return Response.status(500).entity("Error : " + e.getMessage()).build();
				}
			}
		}
		
		return Response.status(500).entity("GradeCard already generated for student").build();
	}
}
