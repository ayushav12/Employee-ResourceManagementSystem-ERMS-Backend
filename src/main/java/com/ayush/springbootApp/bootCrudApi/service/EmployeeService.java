package com.ayush.springbootApp.bootCrudApi.service;

import java.util.List;

import com.ayush.springbootApp.bootCrudApi.model.Employee;

public interface EmployeeService {
	
	List<Employee> get(); //returns list of all employees
	
	Employee get(int id); //returns single employee details based on id
	
	void save(Employee emp); //to add new employee
	
	void delete(int id); //to delete based on id
}
