/**
 * 
 */
package com.crs.flipkart.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.crs.flipkart.bean.*;
import com.crs.flipkart.business.*;

/**
 * @author Shubham
 *
 */
public class CRSStudent {

	Scanner sc = new Scanner(System.in);
	SemesterRegistrationInterface semesterRegistrationService = new SemesterRegistrationService();
	StudentInterface studentService = new StudentService();
	static boolean submittedCourses;
	static boolean isRegistered;
	
	public void create_menu(String studentId) {
		int choice;
		do {

				submittedCourses = studentService.submittedCourses(studentId);
				isRegistered = studentService.isRegistered(studentId);
				System.out.println("---------------Student Dashboard---------------");
				System.out.println("1. View Courses");
				System.out.println("2. Semester Registration");
				System.out.println("3. View Registered Courses");
				System.out.println("4. View grade card");
				System.out.println("5. Make Payment");
				System.out.println("6. Logout");
				System.out.print("Option : ");
			
				choice = sc.nextInt();
			
				switch (choice) {
				
				case 1:
					viewCourses();
					break;
					
				case 2: 
					registerSem(studentId);
					break;
					
				case 3:
					viewRegisteredCourses(studentId);
					break;
					
				case 4:
					viewGradeCard(studentId);
					break;
					
				case 5:
					makePayment(studentId);
					break;
					
				case 6:
					CRSApplication.loggedIn = false;
					break;			
					
				default:
					System.out.println("Incorrect Choice!");
			}
			
		}while(CRSApplication.loggedIn);
	}
	
	public void viewCourses()
	{
		// Display all the courses from the catalog
		List<Course> courseList = new ArrayList<Course>();
		courseList = semesterRegistrationService.viewCourses();
		for(Course course: courseList)
		{
			if(course.getStudentCount()<10)
				System.out.println(course.getCourseId() +" : "+ course.getCourseName()+" taught by : "+course.getProfessorId());
		}
	}
	public void registerSem(String studentID)
	{
		if(submittedCourses)
		{
			System.out.println("You have already finalized your courses.");
			return;
		}
		int choice;
		do
		{
			System.out.println("---------------Student Registration Dashboard---------------");
			System.out.println("1. View Courses");
			System.out.println("2. Add Course");
			System.out.println("3. Drop Course");
			System.out.println("4. View Opted Courses");
			System.out.println("5. Submit the choices");
			System.out.println("6. Exit");
			System.out.println("Option : ");
			choice = sc.nextInt();
			switch(choice)
			{
			case 1:
				viewCourses();
				break;
			case 2:
				addCourse(studentID);
				break;
			case 3:
				dropCourse(studentID);
				break;
			case 4:
				viewOptedCourses(studentID);
				break;
			case 5:
				submitChoice(studentID);
				break;
			case 6:
				break;
			default:
				System.out.println("Invalid option");
			}
		}while(choice!=6);
	}
	public void addCourse(String studentID)
	{
		System.out.print("Enter the course id : ");
		sc.nextLine();
		String courseID = sc.nextLine();
//		System.out.println(courseID);
		String status=semesterRegistrationService.addCourse(studentID,courseID);
		System.out.println(status);
	}
	public void dropCourse(String studentID)
	{
		System.out.print("Enter the course id : ");
		sc.nextLine();
		String courseID = sc.nextLine();
		String status=semesterRegistrationService.dropCourse(studentID,courseID);
		System.out.println(status);
	}
	public void viewOptedCourses(String studentID)
	{
		List<Course> courseList = new ArrayList<Course>();
		courseList = semesterRegistrationService.viewOptedCourses(studentID);
		for(Course course: courseList)
		{
			System.out.println(course.getCourseId() +" : "+ course.getCourseName());
		}
	}
	public void submitChoice(String studentID)
	{
		String status = semesterRegistrationService.submitOptedCourses(studentID);
		System.out.println(status);
	}
	public void viewRegisteredCourses(String studentID)
	{
		if(!submittedCourses)
		{
			System.out.println("You have not yet finalized the courses.");
			return;
		}
		List<Course> courseList = new ArrayList<Course>();
		courseList = studentService.viewRegisteredCourses(studentID);
		for(Course course: courseList)
		{
			System.out.println(course.getCourseId() +" : "+ course.getCourseName());
		}
		if(!isRegistered)
		{
			System.out.println("Please pay your fees as soon as possible.");
		}
	}
	public void viewGradeCard(String studentID)
	{
		if(!isRegistered)
		{
			System.out.println("You have not completed the registration process.");
			return;
		}
		GradeCard gradeCard = studentService.viewGradeCard(studentID);
		System.out.println("_____________Grade Card_______________");
		System.out.println("ID : "+gradeCard.getStudentEnrollmentId()+"\tSemester : "+gradeCard.getSemester());
		for(Grade grade:gradeCard.getGradeList())
		{
			System.out.println(grade.getCourseId()+" - "+grade.getStudentGrade());
		}
		System.out.println("CGPA : "+gradeCard.getStudentCgpa());
		System.out.println("________________________________________");
	}
	public void makePayment(String studentID)
	{
		if(!submittedCourses)
		{
			System.out.println("You have not yet finalized the courses.");
			return;
		}
		String[] modeOfPayment = {"Netbanking","Card","Cheque","Cash"};
		
		float totalFee = studentService.getTotalFee(studentID);
		System.out.println("Fees to be paid : "+totalFee);
		System.out.println("1. Netbanking");
		System.out.println("2. Card");
		System.out.println("3. Cheque");
		System.out.println("4. Cash");
		System.out.println("5. Exit");
		System.out.println("Option : ");
		
		int choice = sc.nextInt();
		
		if(choice!=5)
		{
			String status = studentService.makePayment(studentID,modeOfPayment[choice-1],totalFee);
			System.out.println(status);
		}
	}
}
