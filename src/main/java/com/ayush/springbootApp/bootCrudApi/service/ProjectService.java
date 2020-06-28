package com.ayush.springbootApp.bootCrudApi.service;

import java.util.List;

import com.ayush.springbootApp.bootCrudApi.model.Project;

public interface ProjectService{
	
	List<Project> getProjects();
	
	List<Project> getActiveProjects();
	
	Project getProjectById(int id);
	
	void addProject(Project project);
	
	Project updateProject(Project project);
	
	void deleteProject(int id);
}