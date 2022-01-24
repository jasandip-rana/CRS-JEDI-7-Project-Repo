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
			System.out.print("---------------------WELCOME-----------------\n1. Login\n2. Student Registration\n3. Update Password\n4. Exit\nOption : ");
			choice = sc.nextInt();
			switch(choice)
			{
			case 1: // Login
				crsApplication.login();
				break;
			case 2: //Student registration
				crsApplication.registerStudent();
				break;
			case 3: // update password
				break;
			case 4: // exit
				break;
			default:
				System.out.print("Invalid option\n");
			}
		}while(choice!=4);
	}
	public void login()
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.print("-----------------Login------------------\n");
//		System.out.print("Enter your role (Student/Professor/Admin) : ");
//		role = sc.nextLine();

		String userId,password;
			System.out.print("Email:");
			userId = sc.next();
			System.out.print("Password:");
			password = sc.next();
			loggedIn = true;
					
			String role = "Student";
			// Role defaulted to student instead of a database call.
			
			//true->role->student->approved
			
			if(loggedIn)
			{
				 //String role = userInterface.getRole(userId);
				
				
				
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
						System.out.print("Failed to login, you have not been approved by the administration!");
						loggedIn=false;
					}
					break;
				}
				
				
			}
			else
			{
				System.out.print("Invalid Credentials!");
			}
			
	
	}
	public void registerStudent()
	{
		Scanner sc=new Scanner(System.in);

		String userId,name,password,address,branchName, semester;
			//input all the student details
			System.out.print("---------------Student Registration-------------\n");
			System.out.print("Name:");
			name=sc.nextLine();
			System.out.print("Email:");
			userId=sc.next();
			System.out.print("Password:");
			password=sc.next();
			sc.nextLine();
			System.out.print("Branch:");
			branchName=sc.nextLine();
			System.out.print("Batch:");
			semester=sc.nextLine();
			System.out.print("Address:");
			address=sc.nextLine();
			
			
			//String newStudentId = studentInterface.register(name, userId, password, gender, batch, branchName, address);
			
			//notificationInterface.sendNotification(NotificationTypeConstant.REGISTRATION, newStudentId, null,0);
			
	
	}
	

}
