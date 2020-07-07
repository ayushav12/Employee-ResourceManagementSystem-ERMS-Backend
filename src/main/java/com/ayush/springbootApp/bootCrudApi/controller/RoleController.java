package com.ayush.springbootApp.bootCrudApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayush.springbootApp.bootCrudApi.model.Role;
import com.ayush.springbootApp.bootCrudApi.service.RoleService;

@RestController
@RequestMapping("/api")
public class RoleController
{
	@Autowired
	private RoleService roleService;
	
	@PostMapping("/role")
	public String save(@RequestBody Role r)
	{
		return roleService.save(r);
	}
	
	@GetMapping("/role")
	public List<Role> get()
	{
		return roleService.get();
	}
	
	@GetMapping("/role/{id}")
	public Role get(@PathVariable int id)
	{
		return roleService.get(id);
	}
	
	@PutMapping("/role")
	public Role update(@RequestBody Role r)
	{
		return roleService.update(r);
	}
	
}