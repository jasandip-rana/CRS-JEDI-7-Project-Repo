/**
 * 
 */
package com.crs.flipkart.business;

import java.util.List;
import java.util.Random;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.bean.Payment;
import com.crs.flipkart.dao.*;
import com.crs.flipkart.dao.StudentDaoInterface;
import com.crs.flipkart.dao.StudentDaoService;

/**
 * @author Shubham
 *Implementation of Student Services
 */
public class StudentService implements StudentInterface {

	StudentDaoInterface studentDaoService = new StudentDaoService();
	SemesterRegistrationInterface semesterRegistrationService = new SemesterRegistrationService();


    /**
     * method for getting all the registered courses for the student
     * 
     * @param studentId  unique Id to represent a student
     * @return list of courses registered by student 
     */
	public List<Course> viewRegisteredCourses(String studentID) {
		// TODO Auto-generated method stub
		
		return semesterRegistrationService.viewOptedCourses(studentID);
		
	}
	

    /**
     * method for viewing  grade card of the student
     * 
     * @param studentId  unique Id to represent a student
     * @return grade card of the student 
     */

	public GradeCard viewGradeCard(String studentID) {
		// TODO Auto-generated method stub

		return studentDaoService.viewGradeCard(studentID);
	}


    /**
     * method for getting the total fee of all the courses opted by student
     * 
     * @param studentId  unique Id to represent a student
     * @return total fee of all the courses opted by student 
     */
	public float getTotalFee(String studentID) {
		// TODO Auto-generated method stub
		List<Course> registeredCourses =  semesterRegistrationService.viewOptedCourses(studentID);
		float totalFee = 0;
		for(int i = 0;i<4;i++)
		{	
			totalFee+=registeredCourses.get(i).getCourseFee();
		}
		return totalFee;
	}
	
	/**
     * method for paying fee
     *
     * @param studentId  unique Id to represent a student
     * @param modeOfPayment represents the mode of payment
     * @param totalFee represents amount to be paid
     * @return returns string which represents the status of the payment
     */

	public String makePayment(String studentID, String modeOfPayment,float totalFee) {
	
		Payment payment = new Payment();
		
		Random rand = new Random();
        long id = 100000000+rand.nextInt(100000000);
        String refId = Long.toString(id);
       
		
		payment.setStudentEnrollmentId(studentID);
		payment.setAmount(totalFee);
		payment.setPaymentType(modeOfPayment);
		payment.setPaymentStatus(true);
		payment.setReferenceId(refId);
		
		return studentDaoService.makePayment(payment);
		
	}


	/**
     * method for checking that student is approved by administrator or not
     *
     * @param studentId  unique Id to represent a student
     * @return returns true if student is approved by administrator
     */
	public boolean isApproved(String studentId)
	{
		return studentDaoService.isApproved(studentId);
	}
	
	/**
     * method for getting the fee status of the student
     *
     * @param studentId  unique Id to represent a student
     * @return returns true if student paid the fees
     */
	
	public boolean getFeeStatus(String studentId)
	{
		return studentDaoService.getFeeStatus(studentId);
	}
	
	
	 /**
     * method for checking that student has already submitted their course choices or not
     * 
     * @param studentId  unique Id to represent a student
     * @return returns true if course choices already submitted by student
     */
	public boolean submittedCourses(String studentId)
	{
		return studentDaoService.submittedCourses(studentId);
	}
}
