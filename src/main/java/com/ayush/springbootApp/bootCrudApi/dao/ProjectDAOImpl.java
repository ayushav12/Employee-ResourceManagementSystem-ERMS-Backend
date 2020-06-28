package com.ayush.springbootApp.bootCrudApi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ayush.springbootApp.bootCrudApi.model.Project;

@Repository
public class ProjectDAOImpl implements ProjectDAO
{
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Project> getProjects() {
		Session currSession = entityManager.unwrap(Session.class);
		Query<Project> prjQuery= currSession.createQuery("from Project", Project.class);
		List<Project> prjList= prjQuery.getResultList();
		return prjList;
	}

	@Override
	public Project getProjectById(int id) {
		Session currSession = entityManager.unwrap(Session.class);
		Project prj= currSession.get(Project.class,id);
		return prj;
	}

	@Override
	public void addProject(Project project) {
		Session currSession = entityManager.unwrap(Session.class);
		currSession.save(project);
	}

	@Override
	public Project updateProject(Project project) {
		Session currSession = entityManager.unwrap(Session.class);
		currSession.saveOrUpdate(project);
		return project;
	}

	@Override
	public void deleteProject(int id) {
		Session currSession = entityManager.unwrap(Session.class);
		Project prj = currSession.get(Project.class,id);
		currSession.delete(prj);
	}

	@Override
	public List<Project> getActiveProjects() {
		Session currSession=entityManager.unwrap(Session.class);
		CriteriaBuilder cb= currSession.getCriteriaBuilder();
		CriteriaQuery<Project> cr= cb.createQuery(Project.class);
		Root<Project> root=cr.from(Project.class);
		cr.select(root).where(cb.equal(root.get("project_active"), 1));
		List<Project> activeProjs = currSession.createQuery(cr).getResultList();
		return activeProjs;
	}
	
}