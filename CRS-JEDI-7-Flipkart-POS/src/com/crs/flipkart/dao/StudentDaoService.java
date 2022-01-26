/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.Grade;
import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.bean.Payment;
import com.crs.flipkart.constants.SQLQueries;
import com.crs.flipkart.utils.dbUtil;

/**
 * @author Shubham
 *
 */
public class StudentDaoService implements StudentDaoInterface {

	public static Connection conn = dbUtil.getConnection();

	@Override
	public GradeCard viewGradeCard(String studentId) {
		GradeCard gradeCard = new GradeCard();
		try {

			PreparedStatement ps = conn.prepareStatement(SQLQueries.FETCH_GRADE);
			PreparedStatement ps1 = conn.prepareStatement(SQLQueries.FETCH_GRADECARD);

			ps.setString(1, studentId);
			ps1.setString(1, studentId);

			ResultSet rs1 = ps1.executeQuery();
			if (!rs1.next()) {
				return null;
			}

			ResultSet rs = ps.executeQuery();
			List<Grade> gradeList = new ArrayList<Grade>();

			while (rs.next()) {
				String courseId = rs.getString("courseId");
				String studentID = rs1.getString("studentId");
				float gpa = rs1.getFloat("gpa");

				Grade grade = new Grade();
				grade.setCourseId(courseId);
				grade.setStudentEnrollmentId(studentID);
				grade.setStudentGrade(gpa);

				gradeList.add(grade);

			}

			gradeCard.setGradeList(gradeList);

			return gradeCard;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public String makePayment(Payment payment) {
		try {

			PreparedStatement ps = conn.prepareStatement(SQLQueries.ADD_PAYMENT);

			ps.setString(1, payment.getStudentEnrollmentId());
			ps.setString(2, payment.getReferenceId());
			ps.setFloat(3, payment.getAmount());
			ps.setString(4, payment.getPaymentType());

			int rs = ps.executeUpdate();
			if (rs == 0) {
				return "Payment unsuccessfull";
			}

			return "Payment Successfull. Reference Id : " + payment.getReferenceId();
		} catch (SQLException e) {
			e.printStackTrace();
			return "Payment unsuccessfull";
		}

	}
	
	public boolean isApproved(String studentId)
	{
		try {

			PreparedStatement ps = conn.prepareStatement(SQLQueries.IS_APPROVED);

			ps.setString(1, studentId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				if(rs.getInt("verificationStatus")==1)
					return true;
				
			}
			
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
