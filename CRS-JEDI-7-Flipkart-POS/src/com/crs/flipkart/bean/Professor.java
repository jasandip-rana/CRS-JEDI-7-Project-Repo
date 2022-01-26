/**
 * 
 */
package com.crs.flipkart.bean;

import java.util.Date;

/**
 * @author Raj
 *
 */
public class Professor extends User{

	private String professorId;
	private float salary;
	private String department;
	private Date doj;

	/**
	 * @return the professorId
	 */
	public Professor(String userId, String name, String email, String password, int role, 
			 float salary, String department, Date doj) {
        super(userId, name, email, password, role);
        this.professorId=userId;
        this.salary = salary;
        this.department = department;
        this.doj=doj;
    }
	
	public String getProfessorId() {
		return professorId;
	}

	/**
	 * @param professorId the professorId to set
	 */
	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}

	/**
	 * @return the salary
	 */
	public float getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(float salary) {
		this.salary = salary;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the doj
	 */
	public Date getDoj() {
		return doj;
	}

	/**
	 * @param doj the doj to set
	 */
	public void setDoj(Date doj) {
		this.doj = doj;
	}

}
