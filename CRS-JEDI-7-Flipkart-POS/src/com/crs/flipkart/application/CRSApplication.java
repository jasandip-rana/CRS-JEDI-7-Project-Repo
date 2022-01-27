/**
 * 
 */
package com.crs.flipkart.application;

import java.util.Scanner;

import com.crs.flipkart.bean.*;
import com.crs.flipkart.business.StudentInterface;
import com.crs.flipkart.business.StudentService;
import com.crs.flipkart.business.UserInterface;
import com.crs.flipkart.business.UserService;

/**
 * @author Shubham
 *
 */
public class CRSApplication {

	/**
	 * @param args
	 */

	static boolean loggedIn = false;
	UserInterface userService = new UserService();
	StudentInterface studentService = new StudentService();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Main screen will have 4 things

		Scanner sc = new Scanner(System.in);
		CRSApplication crsApplication = new CRSApplication();
		int choice;
		do {
			System.out.println("\n\n_____________________________________________________________________________");
			System.out.println("");
			System.out.println("                    WELCOME TO COURSE REGISTRATION SYSTEM                    ");
			System.out.println("_____________________________________________________________________________");
			System.out.print("\n1. Login\n2. Student Registration\n3. Update Password\n4. Exit\nOption : ");
			choice = sc.nextInt();
			switch (choice) {
			case 1: // Login
				crsApplication.login();
				break;
			case 2: // Student registration
				crsApplication.registerStudent();
				break;
			case 3: // update password
				crsApplication.updatePassword();
				break;
			case 4: // exit
				break;
			default:
				System.out.print("Invalid option\n");
			}
		} while (choice != 4);
	}

	public void login() {
		Scanner sc = new Scanner(System.in);

		System.out.println("\n\n_____________________________________________________________________________");
		System.out.println("");
		System.out.println("                                    LOGIN                                    ");
		System.out.println("_____________________________________________________________________________\n");
		String email, password;
		System.out.print("Email:");
		email = sc.next();
		System.out.print("Password:");
		password = sc.next();
		User user = userService.login(email, password);
		

		if (user!=null) {
			CRSApplication.loggedIn=true;
			switch (user.getRole()) {
			
			case "Admin":
				CRSAdmin admin=new CRSAdmin ();
				admin.create_menu();
				break;
			case "Professor":
				String professorId=user.getUserId();
				CRSProfessor professor = new CRSProfessor();
				professor.create_menu(professorId);
				break;
			case "Student":
				String studentId = user.getUserId();
				boolean isApproved = studentService.isApproved(studentId);
				if (isApproved) {
					CRSStudent student = new CRSStudent();
					student.create_menu(studentId);

				} else {
					System.out.print("Failed to login, you have not been approved by the administration!");
					loggedIn = false;
				}
				break;
			}

		} else {
			System.out.print("Invalid Credentials!");
		}

	}
	public void registerStudent() {
		Scanner sc = new Scanner(System.in);

		String email, name, password, branchName, batch, contactNumber;
		// input all the student details
		System.out.println("\n\n_____________________________________________________________________________");
		System.out.println("");
		System.out.println("                           STUDENT REGISTRATION                              ");
		System.out.println("_____________________________________________________________________________\n");
		System.out.print("Name:");
		name = sc.nextLine();
		System.out.print("Contact: ");
		contactNumber = sc.nextLine();
		System.out.print("Email:");
		email = sc.next();
		System.out.print("Password:");
		password = sc.next();
		sc.nextLine();
		System.out.print("Branch:");
		branchName = sc.nextLine();
		System.out.print("Batch:");
		batch = sc.nextLine();

		System.out.println(userService.registerStudent(name,contactNumber,email, password, branchName, batch));

	}
	public void updatePassword()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("\n\n_____________________________________________________________________________");
		System.out.println("");
		System.out.println("                             UPDATE PASSWORD                                 ");
		System.out.println("_____________________________________________________________________________\n");

		String email, oldPassword, newPassword;
		System.out.print("Email:");
		email = sc.next();
		System.out.print("Old Password:");
		oldPassword = sc.next();
		System.out.print("New Password:");
		newPassword = sc.next();
		System.out.println(userService.updatePassword(email, oldPassword,newPassword));
	}

}
