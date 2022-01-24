/**
 * 
 */
package com.crs.flipkart.business;

import java.util.ArrayList;
import java.util.List;

import com.crs.flipkart.bean.Course;

/**
 * @author jasan
 *
 */
public class CatalogService implements CatalogInterface{

	Course course1 =  new Course("101","Mathematics","Computer Science","P01",new ArrayList<String>(),(float) 1000.5);
	Course course2 =  new Course("102","English","Languages","P02",new ArrayList<String>(),(float) 1500.00);
	Course course3 =  new Course("103","Political Science","Law","P03",new ArrayList<String>(),(float) 500.75);
	
	List<Course> courseList = new ArrayList<Course>() {
		{add(course1); add(course2); add(course3);}
	};
	
	public List<Course> viewCourses() {
		// TODO Auto-generated method stub
		return courseList;
	}
}
