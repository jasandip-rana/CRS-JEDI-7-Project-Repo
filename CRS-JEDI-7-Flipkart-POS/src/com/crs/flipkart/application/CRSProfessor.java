/**
 * 
 */
package com.crs.flipkart.application;

import java.util.List;
import java.util.Scanner;

import com.crs.flipkart.bean.*;
import com.crs.flipkart.business.ProfessorInterface;
import com.crs.flipkart.business.ProfessorService;
import com.crs.flipkart.exceptions.CourseNotAvailableException;
import com.crs.flipkart.exceptions.CourseNotFoundException;

/**
 * @author jasan
 *Professor Application
 */
public class CRSProfessor {

	ProfessorInterface professorService=new ProfessorService();
	Scanner sc=new Scanner(System.in);
	public static String id;
	
	/**
     * method for displaying the professor dashboard and selecting the available choices.
     */
	public void create_menu(String professorId)
	{
		Scanner sc = new Scanner(System.in);
		id=professorId;
		int choice;
		do {

				System.out.println("\n\n_____________________________________________________________________________");
				System.out.println("");
				System.out.println("                           PROFESSOR DASHBOARD                               ");
				System.out.println("_____________________________________________________________________________\n");
				System.out.println("1. View Available Courses");
				System.out.println("2. View Selected Courses");
				System.out.println("3. Select Courses");
				System.out.println("4. Grade Student");
				System.out.println("5. View Enrolled Students");
				System.out.println("6. Logout");
				System.out.println("Option : ");
			
				choice = sc.nextInt();
			
				switch (choice) {
				
				case 1:
					
					viewAvailableCourses();
					break;
			
				case 2: 
					viewSelectedCourses(professorId);
					break;
			
				case 3: 
			
					selectCourses();
					break;
					
				case 4:
					
					gradeStudent();
					break;
					
				case 5:
					viewEnrolledStudents();
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
	 * Method to print a course
	 * @param course : which is to be printed
	 */
	public static void printCourse(Course course)
	{
		System.out.println(course.getCourseId() +" : "+ course.getCourseName());
	}
    
	 /**
     * method for viewing available courses.
     */
	public void viewAvailableCourses() {
		// TODO Auto-generated method stub
		List<Course>courseList=professorService.viewCourses();
		courseList.forEach(CRSProfessor::printCourse);
	}
	
	 /**
     * method for selected courses.
     */
	public void viewSelectedCourses(String professorId) {
		List<Course>courseList=professorService.viewSelectedCourses(professorId);
		courseList.forEach(CRSProfessor::printCourse);
	}
	
	/**
     * method for selecting a course
     */
	public void selectCourses() {
		// TODO Auto-generated method stub
		System.out.print("Enter course Id for selecting course: ");
		String courseId=sc.next();
		try {			
			professorService.indicateCourse(id, courseId);
			System.out.println("Course selected successfully");
		}
		catch(Exception e) {
			System.out.println("Error : " + e.getMessage());
		}
		
	}
	
	
	/**
    * method for grading a student
    */
	public void gradeStudent() {
		// TODO Auto-generated method stub
		System.out.print("Enter the semester :");
		String semester=sc.next();
		System.out.println("Enter course ID :");
		String courseId=sc.next();
		if(!professorService.validateCourse(courseId, id))
		{
			System.out.println("You don't teach this course");
			return;
		}
		List<Student> enrolledStudents = professorService.viewEnrolledStudents(courseId);
		if(enrolledStudents!=null)
		{
			int i=1;
			for(Student student:enrolledStudents)
			{
				System.out.println(i+". ID : "+student.getStudentEnrollmentId()+"	Name : "+student.getName());
				i++;
			}
			System.out.print("Enter the student index :");
			int index=sc.nextInt();
			sc.nextLine();
			String studentId=enrolledStudents.get(index-1).getStudentEnrollmentId();
			System.out.println("Enter the grade :");
			float grade=sc.nextFloat();
		    System.out.println(professorService.gradeStudent(studentId, courseId, grade, semester));
		}
		else
			System.out.println("No student enrolled for this course!");
		
	}
     
	/**
     * method for viewing enrolled students in a course
     */
	public void viewEnrolledStudents() {
		// TODO Auto-generated method stub
		System.out.println("Enter course ID :");
		String courseId=sc.next();
		if(!professorService.validateCourse(courseId, id))
		{
			System.out.println("You don't teach this course");
			return;
		}
		List<Student>enrolledStudents=professorService.viewEnrolledStudents(courseId);
		if(enrolledStudents.size() == 0) {
			System.out.println("No student enrolled for this course!");
			return;
		}
		int i=1;
		for(Student student:enrolledStudents)
		{
			System.out.println(i+". ID : "+student.getStudentEnrollmentId()+"	Name : "+student.getName());
			i++;
		}
	}
	
	
}
