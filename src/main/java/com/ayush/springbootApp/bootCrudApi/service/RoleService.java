package com.ayush.springbootApp.bootCrudApi.service;

import java.util.List;

import com.ayush.springbootApp.bootCrudApi.model.Role;

public interface RoleService
{
	List<Role> get();
	
	String save(Role r);
	
	Role get(int id);
	
	Role update(Role r);
}