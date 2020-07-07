package com.ayush.springbootApp.bootCrudApi.dao;

import java.util.List;

import com.ayush.springbootApp.bootCrudApi.model.Role;

public interface RoleDAO
{
	List<Role> get();
	
	String save(Role r);
	
	Role get(int id);
	
	Role update(Role r);
}