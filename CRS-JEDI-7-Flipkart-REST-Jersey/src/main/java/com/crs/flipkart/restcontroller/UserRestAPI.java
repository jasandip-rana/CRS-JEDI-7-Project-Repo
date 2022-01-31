/**
 * 
 */
package com.crs.flipkart.restcontroller;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.crs.flipkart.bean.*;
import com.crs.flipkart.business.*;
import com.crs.flipkart.exceptions.UserNotFoundException;

/**
 * @author Shubham
 *
 */
@Path("/user")
public class UserRestAPI {
	
	UserInterface userService=new UserService();
	StudentInterface studentService = new StudentService();
	
	/**
	 * 
	 * @param email: email address of the user
	 * @param oldPassword: old password to be stored in db.
	 * @param newPassword: new password to be stored in db.
	 * @return @return 201, if password is updated, else 500 in case of error
	 */
	@POST
	@Path("/updatePassword/{email}/{oldPassword}/{newPassword}")
	public Response updatePassword(
			@NotNull
			@PathParam("email") String email,
			@NotNull
			@PathParam("oldPassword") String oldPassword,
			@NotNull
			@PathParam("newPassword") String newPassword) {
		try
		{
			System.out.println(userService.updatePassword(email, oldPassword, newPassword));
			if(UserService.user!=null)
			{
				UserService.user.setPassword(newPassword);
			}
			return Response.status(201).entity("Password updated successfully! ").build();
		}
		catch(UserNotFoundException e)
		{
			return Response.status(500).entity("Email or password is incorrect!").build();
		}
	}
	
	
	/**
	 * 
	 * @param email
	 * @param password
	 * @return 
	 */
	@POST
	@Path("/login/{email}/{password}")
	public Response verifyCredentials(
			@NotNull
			@PathParam("email") String email,
			@NotNull
			@PathParam("password") String password) {
			
		try 
		{
			System.out.println(email+" "+ password);
			User user=userService.login(email, password);
			
					System.out.println("logged in");
					String role=user.getRole();
					System.out.println("got role");
					switch(role)
					{
					case "Student":
						String studentId=user.getUserId();
						boolean isApproved=studentService.isApproved(studentId);
						if(!isApproved)	
						{
							return Response.status(200).entity("Login unsuccessful! Student has not been approved by the administration!" ).build();
						}
						break;
					}
					UserService.user=user;
					return Response.status(200).entity("Login successful").build();
		}
		catch (UserNotFoundException e) 
		{
			return Response.status(500).entity("Invalid credentials!").build();
		}		
		
}
	
	/**
	 * 
	 * @param student
	 * @return 201, if user is created, else 500 in case of error
	 */
	@POST
	@Path("/studentRegistration")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(Student student)
	{
		
		try
		{
			userService.registerStudent(student.getName(),student.getContactNumber(), student.getEmail(), student.getPassword(), student.getBranch(), student.getBatch());
		}
		catch(Exception ex)
		{
			return Response.status(500).entity("Something went wrong! Please try again.").build(); 
		}
		
		
		return Response.status(201).entity("Registration Successful for "+student.getName()).build(); 
	}
	
	
	@POST
    @Path("/logout")
    public Response logout() {
        UserService.user=null;
        return Response.status(200).entity("User logged out Successfully").build();
    }
}
