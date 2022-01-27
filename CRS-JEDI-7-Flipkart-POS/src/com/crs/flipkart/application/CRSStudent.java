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
	static boolean feeStatus;
	
	/**
	 * Method to display student dash-board
	 * 
	 * @param studentId of the student
	 */
	public void create_menu(String studentId) {
		int choice;
		do {

				submittedCourses = studentService.submittedCourses(studentId);
				feeStatus = studentService.getFeeStatus(studentId);
				System.out.println("\n\n_____________________________________________________________________________");
				System.out.println("");
				System.out.println("                              STUDENT DASHBOARD                              ");
				System.out.println("_____________________________________________________________________________\n");
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
	
	/**
	 * Method to display all the courses from the catalog
	 */
	public void viewCourses()
	{
		List<Course> courseList = new ArrayList<Course>();
		courseList = semesterRegistrationService.viewCourses();
		for(Course course: courseList)
		{
			if(course.getStudentCount()<10)
				System.out.println(course.getCourseId() +" : "+ course.getCourseName()+" taught by : "+course.getProfessorId());
		}
	}
	
	/**
	 * Method to display semester registration dash-board
	 * 
	 * @param studentId of the student
	 */
	public void registerSem(String studentID)
	{
		if(submittedCourses)
		{
			System.out.println("You have already finalized your courses.");
			return;
		}
		int choice;
		boolean submittedChoice=false;
		do
		{
			System.out.println("\n\n_____________________________________________________________________________");
			System.out.println("");
			System.out.println("                      SEMESTER REGISTRATION DASHBOARD                        ");
			System.out.println("_____________________________________________________________________________\n");
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
				submittedChoice=submitChoice(studentID);
				break;
			case 6:
				break;
			default:
				System.out.println("Invalid option");
			}
			if(submittedChoice)
				break;
		}while(choice!=6);
	}
	
	/**
	 * Method to add course to opted course for a given student
	 * 
	 * @param studentId of the student
	 */
	public void addCourse(String studentID)
	{
		System.out.print("Enter the course id : ");
		sc.nextLine();
		String courseID = sc.nextLine();
		String status=semesterRegistrationService.addCourse(studentID,courseID);
		System.out.println(status);
	}
	
	/**
	 * Method to drop courses from opted course for a given student
	 * 
	 * @param studentId of the student
	 */
	public void dropCourse(String studentID)
	{
		System.out.print("Enter the course id : ");
		sc.nextLine();
		String courseID = sc.nextLine();
		String status=semesterRegistrationService.dropCourse(studentID,courseID);
		System.out.println(status);
	}
	
	/**
	 * Method to view the list of opted courses for a given student
	 * 
	 * @param studentId of the student
	 */
	public void viewOptedCourses(String studentID)
	{
		List<Course> courseList = new ArrayList<Course>();
		courseList = semesterRegistrationService.viewOptedCourses(studentID);
		for(Course course: courseList)
		{
			System.out.println(course.getCourseId() +" : "+ course.getCourseName());
		}
	}
	
	/**
	 * Method to register for the opted course for a given student
	 * 
	 * @param studentId of the student
	 * @return returns a boolean that indicates if the courses are successfully alloted in the database
	 */
	public boolean submitChoice(String studentID)
	{
		String status = semesterRegistrationService.submitOptedCourses(studentID);
		System.out.println(status);
		if(!(status.equals("Please select some other courses as some courses are filled and dropped from the list")||status.equals("Please select atleast 4 courses")))
			return true;
		return false;
	}
	
	
	/**
	 * Method to view the list of registered courses for a given student
	 * 
	 * @param studentId of the student
	 */
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
		if(!feeStatus)
		{
			System.out.println("Please pay your fees as soon as possible.");
		}
	}
	
	/**
	 * Method to view the grade card for a given student
	 * 
	 * @param studentId of the student
	 */
	public void viewGradeCard(String studentID)
	{
		if(!feeStatus)
		{
			System.out.println("You have not paid the fees yet.");
			return;
		}
		GradeCard gradeCard = studentService.viewGradeCard(studentID);
		if(gradeCard==null)
		{
			System.out.println("Grade card not yet generated");
			return;
		}
		System.out.println("\n\n___________________________________________________________________");
		System.out.println("");
		System.out.println("                            GRADE CARD                             ");          
		System.out.println("___________________________________________________________________\n");
		System.out.println("ID : "+gradeCard.getStudentEnrollmentId()+"\tSemester : "+gradeCard.getSemester());
		for(Grade grade:gradeCard.getGradeList())
		{
			System.out.println(grade.getCourseId()+" - "+grade.getStudentGrade());
		}
		System.out.println("CGPA : "+gradeCard.getStudentCgpa());
		System.out.println("\n___________________________________________________________________");
	}
	
	
	/**
	 * Method to make payment for a given student
	 * 
	 * @param studentId of the student
	 */
	public void makePayment(String studentID)
	{
		if(!submittedCourses)
		{
			System.out.println("You have not yet finalized the courses.");
			return;
		}
		if(feeStatus)
		{
			System.out.println("You have paid the fees");
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
