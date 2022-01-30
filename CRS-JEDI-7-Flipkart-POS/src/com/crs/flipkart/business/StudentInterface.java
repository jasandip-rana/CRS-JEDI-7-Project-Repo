/**
 * 
 */
package com.crs.flipkart.business;

import java.util.List;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.bean.Payment;
import com.crs.flipkart.exceptions.GradeCardNotGeneratedException;

/**
 * @author jasan
 *
 */
public interface StudentInterface {

	/**
     * method for checking that student is approved by administrator or not
     *
     * @param studentId  unique Id to represent a student
     * @return returns true if student is approved by administrator
     */
	boolean isApproved(String studentId);
	
	 /**
     * method for checking that student has already submitted their course choices or not
     * 
     * @param studentId  unique Id to represent a student
     * @return returns true if course choices already submitted by student
     */
	boolean submittedCourses(String studentId);
	
	/**
     * method for getting the fee status of the student
     *
     * @param studentId  unique Id to represent a student
     * @return returns true if student paid the fees
     */
	boolean getFeeStatus(String studentId);
	
	 /**
     * method for getting all the registered courses for the student
     * 
     * @param studentId  unique Id to represent a student
     * @return list of courses registered by student 
     */
	List<Course> viewRegisteredCourses(String studentID);
	
	/**
     * method for getting the total fee of all the courses opted by student
     * 
     * @param studentId  unique Id to represent a student
     * @return total fee of all the courses opted by student 
     */
	float getTotalFee(String studentID);
	
	/**
     * method for paying fee
     *
     * @param studentId  unique Id to represent a student
     * @param modeOfPayment represents the mode of payment
     * @param totalFee represents amount to be paid
     * @return returns string which represents the status of the payment
     */
	String makePayment(String studentID, String modeOfPayment,float totalFee);
	
	/**
     * method for viewing  grade card of the student
     * 
     * @param studentId  unique Id to represent a student
     * @return grade card of the student 
     */
	GradeCard viewGradeCard(String studentID) throws GradeCardNotGeneratedException;
}
