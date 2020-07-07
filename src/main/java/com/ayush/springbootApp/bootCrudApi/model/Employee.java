package com.ayush.springbootApp.bootCrudApi.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table (name="employee_table")
//model class for Employee
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String name;
	@Column
	private String gender;
	@Column
	private String department;
	@Column
	private String designation;
	@Column
	private Date dob;
	@Column(name="empcode",nullable=false,unique=true)
	private String empCode;
	
	@OneToOne(mappedBy="emp")
	private Workstation workStation;
	
	//old mapping commented and now handled by EmployeeProjectRole.java and below object definition
	//@ManyToMany
	//@JoinTable(name="emp_project_mapping",joinColumns=@JoinColumn(name="emp_id"),inverseJoinColumns=@JoinColumn(name="project_id"))
	//private Set<Project> prj=new HashSet<Project>();
	
	@OneToMany(mappedBy="emp")
	private List<EmployeeProjectRole> epr;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	
	
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", code="+ empCode + ", name=" + name + ", gender=" + gender + ", department=" + department
				+ ", designation=" + designation + ", dob=" + dob + "]";
	} 
}
