package com.ayush.springbootApp.bootCrudApi.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.ayush.springbootApp.bootCrudApi.model.Employee;

@Entity
@Table(name="projects_table")
public class Project{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column(nullable=false)
	private String oea_number;
	@Column(nullable=false)
	private String project_name;
	@Column
	private String client_name;
	@Column
	private String origin_country;
	@Column(nullable=false)
	private Integer po_amount;
	@Column
	private int project_active;
	
	@ManyToMany(mappedBy="prj")
	private Set<Employee> emp=new HashSet<Employee>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOea_number() {
		return oea_number;
	}
	public void setOea_number(String oea_number) {
		this.oea_number = oea_number;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public String getOrigin_country() {
		return origin_country;
	}
	public void setOrigin_country(String origin_country) {
		this.origin_country = origin_country;
	}
	public Integer getPo_amount() {
		return po_amount;
	}
	public void setPo_amount(Integer po_amount) {
		this.po_amount = po_amount;
	}
	public int getProject_active()
	{
		return this.project_active;
	}
	public void setProject_active(int project_active)
	{
		this.project_active=project_active;
	}
	public Set<Employee> getEmployee()
	{
		return this.emp;
	}
	public void setEmployee(Set<Employee> emp)
	{
		this.emp=emp;
	}
	
	@Override
	public String toString() {
		return "Project [id=" + id + ", oea_number=" + oea_number + ", project_name=" + project_name + ", client_name="
				+ client_name + ", origin_country=" + origin_country + ", po_amount=" + po_amount + "]";
	}
	
	
}