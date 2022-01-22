/**
 * 
 */
package com.crs.flipkart.application;

import java.util.Scanner;

/**
 * @author Shubham
 *
 */
public class CRSStudent {

	Scanner sc = new Scanner(System.in);
	private boolean is_registered;
	
	public void create_menu(String studentId) {
		
		is_registered = true;//getRegistrationStatus(studentId);
		int choice;
		do {
			
				System.out.println("---------------Student Dashboard---------------");
				System.out.println("1. View Courses");
				System.out.println("2. Semester Registration");
				System.out.println("3. View Registered Courses");
				System.out.println("4. View grade card");
				System.out.println("5. Make Payment");
				System.out.println("6. Logout");
			
				choice = sc.nextInt();
			
				switch (choice) {
				
				case 1:
					viewCourses(studentId);
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
					make_payment(studentId);
					break;
					
				case 6:
					CRSApplication.loggedIn = false;
					break;			
					
				default:
					System.out.println("Incorrect Choice!");
			}
			
		}while(CRSApplication.loggedIn);
	}
}
