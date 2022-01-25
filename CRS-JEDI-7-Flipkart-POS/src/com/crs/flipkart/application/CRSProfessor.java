/**
 * 
 */
package com.crs.flipkart.application;

import java.util.Scanner;

/**
 * @author jasan
 *
 */
public class CRSProfessor {

	
	public void create_menu()
	{
		Scanner sc = new Scanner(System.in);
//		is_registered = true;//getRegistrationStatus(studentId);
		boolean loggedIn = true;
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
		
	}

	public void selectCourses() {
		// TODO Auto-generated method stub
		
	}

	public void gradeStudent() {
		// TODO Auto-generated method stub
		
	}

	public void viewEnrolledStudents() {
		// TODO Auto-generated method stub
		
	}
	
	
}
