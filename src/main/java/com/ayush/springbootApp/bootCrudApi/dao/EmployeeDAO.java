package com.ayush.springbootApp.bootCrudApi.dao;

import java.util.List;
import com.ayush.springbootApp.bootCrudApi.model.Employee;

public interface EmployeeDAO {

	List<Employee> get(); //returns list of all employees
	
	Employee get(int id); //returns single employe details based on id
	
	void save(Employee emp); //to add new employee
	
	void delete(int id); //to delete based on id
	
}
