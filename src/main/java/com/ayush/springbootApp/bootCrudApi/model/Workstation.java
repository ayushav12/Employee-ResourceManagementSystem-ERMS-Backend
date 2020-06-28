package com.ayush.springbootApp.bootCrudApi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_workstation")
public class Workstation{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column(unique=true)
	private String seat_no;
	
	@Column
	private int floor_no;
	
	@Column
	private String office_location;
	
	//@OneToOne(cascade=CascadeType.ALL) //gave error "detached entity passed to persist" because Employee is already in the DB and with MERGE Employee is automatically merged instead
	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="allocated_user")
	private Employee emp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSeat_no() {
		return seat_no;
	}

	public void setSeat_no(String seat_no) {
		this.seat_no = seat_no;
	}

	public int getFloor_no() {
		return floor_no;
	}

	public void setFloor_no(int floor_no) {
		this.floor_no = floor_no;
	}

	public String getOffice_location() {
		return office_location;
	}

	public void setOffice_location(String office_location) {
		this.office_location = office_location;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}
}