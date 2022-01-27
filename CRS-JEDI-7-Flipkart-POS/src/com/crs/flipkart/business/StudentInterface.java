/**
 * 
 */
package com.crs.flipkart.business;

import java.util.List;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.bean.Payment;

/**
 * @author jasan
 *
 */
public interface StudentInterface {

	public List<Course> viewRegisteredCourses(String studentID);
	public GradeCard viewGradeCard(String studentID);
	public float getTotalFee(String studentID);
	public String makePayment(String studentID, String modeOfPayment,float totalFee);
	public boolean isApproved(String studentId);
	public boolean getFeeStatus(String studentId);
	public boolean submittedCourses(String studentId);
}
