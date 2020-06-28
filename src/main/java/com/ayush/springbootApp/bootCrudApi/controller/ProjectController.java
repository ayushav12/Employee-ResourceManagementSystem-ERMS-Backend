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

import com.ayush.springbootApp.bootCrudApi.model.Project;
import com.ayush.springbootApp.bootCrudApi.service.ProjectService;

@RestController
@RequestMapping("/api")
public class ProjectController
{
	@Autowired
	private ProjectService projectService;
	
	@GetMapping("/projects")
	public List<Project> get()
	{
		return projectService.getProjects();
	}
	
	@GetMapping("/activeProjects")
	public List<Project> getActive()
	{
		return projectService.getActiveProjects();
	}
	
	@GetMapping("/project/{id}")
	public Project get(@PathVariable int id)
	{
		return projectService.getProjectById(id);
	}
	
	@PostMapping("/project")
	public Project add(@RequestBody Project prj)
	{
		projectService.addProject(prj);
		return prj;
	}
	
	@PutMapping("/project")
	public Project update(@RequestBody Project prj)
	{
		return projectService.updateProject(prj);
	}
	
	@DeleteMapping("/project/{id}")
	public void delete(@PathVariable int id)
	{
		projectService.deleteProject(id);
	}
}
