/**
 * 
 */
package com.crs.flipkart.application;

import java.util.Scanner;

/**
 * @author Shubham
 *
 */
public class CRSApplication {

	/**
	 * @param args
	 */

	static boolean loggedIn = false;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Main screen will have 4 things

		Scanner sc= new Scanner(System.in); 
		CRSApplication crsApplication = new CRSApplication();
		int choice;
		do
		{
			System.out.print("---------------------WELCOME-----------------\n1. Login\n2. Student Registration\n3. Exit\nOption : ");
			choice = sc.nextInt();
			switch(choice)
			{
			case 1: // Login
				crsApplication.login();
				break;
			case 2: //Student registration
				break;
			case 3: // exit
				break;
			default:
				System.out.println("Invalid option\n");
			}
		}while(choice!=3);
	}
	public void login()
	{
		Scanner sc = new Scanner(System.in);

		String userId,password;
			System.out.println("-----------------Login------------------");
			System.out.println("Email:");
			userId = sc.next();
			System.out.println("Password:");
			password = sc.next();
			loggedIn = true;
						
			//true->role->student->approved
			if(loggedIn)
			{
				 //String role = userInterface.getRole(userId);
				String role;
				System.out.println("Enter your role (Student/Professor/Admin) : ");
				role = sc.nextLine();
				switch(role) {
				case "Admin":
					// Admin functionality
					break;
				case "Professor":
					// Professor functionality
					break;
				case "Student":
					
					String studentId = userId;
					boolean isApproved=true;//studentInterface.isApproved(studentId);
					if(isApproved) {
						CRSStudent student=new CRSStudent();
						student.create_menu(studentId);
						
					} else {
						System.out.println("Failed to login, you have not been approved by the administration!");
						loggedIn=false;
					}
					break;
				}
				
				
			}
			else
			{
				System.out.println("Invalid Credentials!");
			}
			
	
	}
	public void registerStudent()
	{
		Scanner sc=new Scanner(System.in);

		String userId,name,password,address,branchName, semester;
			//input all the student details
			System.out.println("---------------Student Registration-------------");
			System.out.println("Name:");
			name=sc.nextLine();
			System.out.println("Email:");
			userId=sc.next();
			System.out.println("Password:");
			password=sc.next();
			sc.nextLine();
			System.out.println("Branch:");
			branchName=sc.nextLine();
			System.out.println("Batch:");
			semester=sc.nextLine();
			System.out.println("Address:");
			address=sc.nextLine();
			
			
			//String newStudentId = studentInterface.register(name, userId, password, gender, batch, branchName, address);
			
			//notificationInterface.sendNotification(NotificationTypeConstant.REGISTRATION, newStudentId, null,0);
			
	
	}
	

}
