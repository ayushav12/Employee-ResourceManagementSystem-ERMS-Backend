package com.ayush.springbootApp.bootCrudApi.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.ayush.springbootApp.bootCrudApi.model.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Employee> get() {
		Session currSession=entityManager.unwrap(Session.class);
		Query<Employee> query= currSession.createQuery("from Employee", Employee.class);
		List<Employee> empList=query.getResultList();
		return empList;
	}

	@Override
	@Cacheable(value="Employee",key="#id",condition="#id>0")
	public Employee get(int id) {
		Session currSession=entityManager.unwrap(Session.class);
		Employee emp=currSession.get(Employee.class, id);
		return emp;
	}

	@Override
	public void save(Employee emp) {
		System.out.println("Employee Object\n:"+emp.toString());
		Session currSession=entityManager.unwrap(Session.class);
		currSession.save(emp);
		//currSession.saveOrUpdate(emp); //sasveOrUpdate() method updates when an ID is passed to URL and inserts when no ID is passed
		
	}

	@Override
	public void delete(int id) {
		Session currSession=entityManager.unwrap(Session.class);
		Employee emp=currSession.get(Employee.class, id);
		currSession.delete(emp);
	}

}
