package com.ayush.springbootApp.bootCrudApi.dao;

import java.util.List;
import com.ayush.springbootApp.bootCrudApi.model.Project;

public interface ProjectDAO{
	
	List<Project> getProjects();
	
	Project getProjectById(int id);
	
	void addProject(Project project);
	
	Project updateProject(Project project);
	
	void deleteProject(int id);
	
	List<Project> getActiveProjects();
}