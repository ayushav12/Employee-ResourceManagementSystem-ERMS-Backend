package com.ayush.springbootApp.bootCrudApi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayush.springbootApp.bootCrudApi.dao.ProjectDAO;
import com.ayush.springbootApp.bootCrudApi.model.Project;

@Service
public class ProjectServiceImpl implements ProjectService
{
	@Autowired
	private ProjectDAO projectDAO;
	
	@Transactional
	@Override
	public Project getProjectById(int id)
	{
		return projectDAO.getProjectById(id);
	}

	@Transactional	
	@Override
	public List<Project> getProjects() {
		return projectDAO.getProjects();
	}

	@Transactional
	@Override
	public void addProject(Project project) {
		projectDAO.addProject(project);
	}

	@Transactional
	@Override
	public Project updateProject(Project project) {
		return projectDAO.updateProject(project);
	}

	@Transactional
	@Override
	public void deleteProject(int id) {
		projectDAO.deleteProject(id);
	}

	@Transactional
	@Override
	public List<Project> getActiveProjects() {
		return projectDAO.getActiveProjects();
	}
}