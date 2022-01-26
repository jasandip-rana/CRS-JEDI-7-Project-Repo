/**
 * 
 */
package com.crs.flipkart.application;

import java.util.*;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.business.*;

/**
 * @author jasan
 *
 */
public class CRSAdmin {

	AdminInterface adminService = new AdminService();
	Scanner sc=new Scanner(System.in);
	public void create_menu()
	{
		Scanner sc = new Scanner(System.in);
//		is_registered = true;//getRegistrationStatus(studentId);
		boolean loggedIn = true;
		int choice;
		do {
			
				System.out.println("\n\n\n---------------Admin Dashboard---------------\n\n\n");
				System.out.println("1. View Courses");
				System.out.println("2. Add Courses");
				System.out.println("3. Drop Courses");
				System.out.println("4. Approve Student");
				System.out.println("5. Add Professor");
				System.out.println("6. Drop Professor");
				System.out.println("7. View Professor List");
				System.out.println("8. Generate Grade Card");
				System.out.println("9. Logout");
			
				choice = sc.nextInt();
			
				switch (choice) {
				
				case 1:
					// View Courses
					viewCourses();
					break;
					
				case 2: 
					// Add Course;
					addCourse();
					break;
					
				case 3:
					// Drop Course
					dropCourse();
					break;
					
				case 4:
					approveStudent();
					break;
					
				case 5:
					addProfessor();
					break;
					
				case 6:
					// View Professor List
					dropProfessor();
					break;
					
				case 7:
					viewProfessorList();
					break;
				
				case 8:
					// Generate Grade Card
					generateGradeCard();
					break;
					
				case 9:
					CRSApplication.loggedIn = false;
					break;			
					
				default:
					System.out.println("Incorrect Choice!");
			}
			
		}while(CRSApplication.loggedIn);
	}
	public void viewCourses() {
		// TODO Auto-generated method stub
		List<Course>courseList=adminService.viewCourse();
		for(Course course: courseList)
		{
			
				System.out.println(course.getCourseId() +" : "+ course.getCourseName()+" taught by : "+course.getProfessorId());
		}
		
	}
	public void addCourse() {
		// TODO Auto-generated method stub
		Course course=new Course();
		System.out.print("Enter the course Id :");
		course.setCourseId(sc.next());
		System.out.print("Enter the course name :");
		course.setCourseName(sc.nextLine());
		System.out.print("Enter the course fee :");
		course.setCourseFee(sc.nextFloat());
		System.out.println("Enter the department name :");
		course.setDepartmentName(sc.nextLine());
		course.setProfessorId(null);
		course.setStudentCount(0);
		System.out.println(adminService.addCourse(course));
	}
	public void dropCourse() {
		// TODO Auto-generated method stub
		System.out.print("Enter the course Id :");
		String courseId=sc.next();
		System.out.println(adminService.dropCourse(courseId));
		
	}
	public void approveStudent() {
		// TODO Auto-generated method stub
		
		
	}
	public void addProfessor() {
		// TODO Auto-generated method stub
		
	}
	public void dropProfessor() {
		// TODO Auto-generated method stub
		
	}
	public void viewProfessorList() {
		// TODO Auto-generated method stub
		
	}
	public void generateGradeCard() {
		// TODO Auto-generated method stub
		System.out.println("Enter the student Id :");
		String studentId=sc.next();
		System.out.println("Enter the semester :");
		String semester=sc.next();
		System.out.println(adminService.generateGradeCard(studentId, semester));
	}
	
	
}
