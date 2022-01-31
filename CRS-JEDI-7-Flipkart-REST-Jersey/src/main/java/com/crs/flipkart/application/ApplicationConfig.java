/**
 * 
 */
package com.crs.flipkart.application;

/**
 * @author Shubham
 *
 */

import org.glassfish.jersey.server.ResourceConfig;

import com.crs.flipkart.restcontroller.*;



public class ApplicationConfig extends ResourceConfig {

	public ApplicationConfig() {

	register(StudentRestAPI.class);
	register(UserRestAPI.class);
	register(ProfessorRestAPI.class);
	register(AdminRestAPI.class);

	}

}