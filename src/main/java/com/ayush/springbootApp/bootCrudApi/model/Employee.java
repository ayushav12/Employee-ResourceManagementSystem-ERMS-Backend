package com.ayush.springbootApp.bootCrudApi.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ayush.springbootApp.bootCrudApi.model.Project;
import com.ayush.springbootApp.bootCrudApi.model.Workstation;

@Entity
//@IdClass(EmpId.class)
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
	
	@ManyToMany
	@JoinTable(name="emp_project_mapping",joinColumns=@JoinColumn(name="emp_id"),inverseJoinColumns=@JoinColumn(name="project_id"))
	private Set<Project> prj=new HashSet<Project>();
	
	
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
	public Set<Project> getProject()
	{
		return this.prj;
	}
	public void setProject(Set<Project> prj)
	{
		this.prj=prj;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", code="+ empCode + ", name=" + name + ", gender=" + gender + ", department=" + department
				+ ", designation=" + designation + ", dob=" + dob + "]";
	} 
}
