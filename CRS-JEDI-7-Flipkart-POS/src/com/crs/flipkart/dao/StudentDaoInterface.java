package com.crs.flipkart.dao;

import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.bean.Payment;

public interface StudentDaoInterface {

	GradeCard viewGradeCard(String studentId);

	String makePayment(Payment payment);
	
	boolean isApproved(String studentId);
	
	boolean getFeeStatus(String studentId);

	boolean submittedCourses(String studentId);

}