/**
 * 
 */
package com.crs.flipkart.restcontroller;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.URIReferenceException;

import com.crs.flipkart.bean.User;


@Path("/CustomerAPI")
public class StudentRestAPI {
	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public User getCustomerDetails() {

		//  clinet --- service ---- dao ----> SQL
       
		User customer=new User();
		customer.setUserId("101");
		customer.setName("Flipcard");
		customer.setRole("mumbai");
		
	   return customer;

	}
}
