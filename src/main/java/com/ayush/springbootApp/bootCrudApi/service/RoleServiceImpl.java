package com.ayush.springbootApp.bootCrudApi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayush.springbootApp.bootCrudApi.dao.RoleDAO;
import com.ayush.springbootApp.bootCrudApi.model.Role;

@Service
class RoleServiceImpl implements RoleService
{
	@Autowired
	private RoleDAO roleDAO;
	
	@Transactional
	@Override
	public List<Role> get() {
		return roleDAO.get();
	}
	
	@Transactional
	@Override
	public String save(Role r) {
		return roleDAO.save(r);
	}

	@Transactional
	@Override
	public Role get(int id) {
		return roleDAO.get(id);
	}

	@Transactional
	@Override
	public Role update(Role r) {
		return roleDAO.update(r);
	}
	
}