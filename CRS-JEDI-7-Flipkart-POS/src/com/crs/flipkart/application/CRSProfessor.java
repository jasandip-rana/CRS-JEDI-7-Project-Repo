/**
 * 
 */
package com.crs.flipkart.application;

import java.util.List;
import java.util.Scanner;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.business.ProfessorInterface;
import com.crs.flipkart.business.ProfessorService;

/**
 * @author jasan
 *
 */
public class CRSProfessor {

	ProfessorInterface professorService=new ProfessorService();
	Scanner sc=new Scanner(System.in);
	public static String id;
	public void create_menu(String professorId)
	{
		Scanner sc = new Scanner(System.in);
		boolean loggedIn = true;
		id=professorId;
		int choice;
		do {
			
				System.out.println("\n\n\n---------------Professor Dashboard---------------\n\n\n");
				System.out.println("1. View Courses");
				System.out.println("2. Select Courses");
				System.out.println("3. Grade Student");
				System.out.println("4. View Enrolled Students");
				System.out.println("5. Logout");
			
				choice = sc.nextInt();
			
				switch (choice) {
				
				case 1:
					
					viewCourses();
					break;
					
				case 2: 
			
					selectCourses();
					break;
					
				case 3:
					
					gradeStudent();
					break;
					
				case 4:
					viewEnrolledStudents();
					break;

				case 5:
					CRSApplication.loggedIn = false;
					break;			
					
				default:
					System.out.println("Incorrect Choice!");
			}
			
		}while(CRSApplication.loggedIn);
	}

	public void viewCourses() {
		// TODO Auto-generated method stub
		List<Course>courseList=professorService.viewCourses();
		for(Course course: courseList)
		{
			
				System.out.println(course.getCourseId() +" : "+ course.getCourseName()+" taught by : "+course.getProfessorId());
		}
			
	}

	public void selectCourses() {
		// TODO Auto-generated method stub
		System.out.print("Enter course Id for selecting course: ");
		String courseId=sc.next();
		System.out.println(professorService.indicateCourse(id, courseId));
		
	}

	public void gradeStudent() {
		// TODO Auto-generated method stub
		System.out.print("Enter the semester :");
		String semester=sc.next();
		System.out.print("Enter the student ID :");
		String studentId=sc.next();
		System.out.println("Enter course ID :");
		String courseId=sc.next();
		System.out.println("Enter the grade :");
		float grade=sc.nextFloat();
	   System.out.println(professorService.gradeStudent(studentId, courseId, grade, semester));
		
	}

	public void viewEnrolledStudents() {
		// TODO Auto-generated method stub
		System.out.println("Enter course ID :");
		String courseId=sc.next();
		List<String>enrolledStudents=professorService.viewEnrolledStudents(courseId);
		for(String student:enrolledStudents)
			System.out.println(student);
		
	}
	
	
}
