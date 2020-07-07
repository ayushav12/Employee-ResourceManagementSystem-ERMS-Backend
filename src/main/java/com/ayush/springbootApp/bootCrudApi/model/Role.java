package com.ayush.springbootApp.bootCrudApi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Role")
public class Role
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="description",nullable=false,unique=true)
	private String desc;
	
	@OneToMany(mappedBy="role") //mappedBy property tells this class's(Role.java) object name in child class(EmployeeProjectRole.java)
	private List<EmployeeProjectRole> epr;
	
	public List<EmployeeProjectRole> getEpr() {
		return epr;
	}

	public void setEpr(List<EmployeeProjectRole> epr) {
		this.epr = epr;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}