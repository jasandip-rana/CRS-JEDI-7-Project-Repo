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


/* ApplicationConfig class extends ResourceConfig class is Class for application configuration. 
 * This class encompasses all the configuration for an application as specified in ess-config.xml and customizations 
 * thereof, including default values.
 */

public class ApplicationConfig extends ResourceConfig {
	
	public ApplicationConfig() {

		//Student Rest API controller
		register(StudentRestAPI.class);
		
		//User Rest API controller
		register(UserRestAPI.class);
		
		//Professor Rest API controller
		register(ProfessorRestAPI.class);
		
		//Admin Rest API controller
		register(AdminRestAPI.class);

	}

}