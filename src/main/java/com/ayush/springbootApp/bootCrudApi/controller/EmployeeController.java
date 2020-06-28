package com.ayush.springbootApp.bootCrudApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayush.springbootApp.bootCrudApi.model.Employee;
import com.ayush.springbootApp.bootCrudApi.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employee")
	public List<Employee> get()
	{
		return employeeService.get();
	}
	
	@PostMapping("/employee")
	public Employee save(@RequestBody Employee empObj)
	{
		employeeService.save(empObj);
		return empObj;
	}
	
	@GetMapping("/employee/{id}")
	public Employee get(@PathVariable int id)
	{
		return employeeService.get(id);
	}
	
	@DeleteMapping("/employee/{id}")
	public String delete(@PathVariable int id)
	{
		employeeService.delete(id);
		return "Employee deleted with ID:"+id;
	}
	
	@PutMapping("/employee")
	public Employee update(@RequestBody Employee empObj)
	{
		employeeService.save(empObj);
		return empObj;
	}
}
