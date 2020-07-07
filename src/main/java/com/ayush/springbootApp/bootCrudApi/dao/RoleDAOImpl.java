package com.ayush.springbootApp.bootCrudApi.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.ayush.springbootApp.bootCrudApi.model.Role;

@Repository
class RoleDAOImpl implements RoleDAO
{
	
	@Autowired
	private EntityManager em;

	@Override
	public List<Role> get() {
		Session sess=em.unwrap(Session.class);
		Query<Role> query= sess.createQuery("from Role", Role.class);
		return query.getResultList();
	}

	@Override
	public String save(Role r) {
		try{
		Session sess=em.unwrap(Session.class);
		sess.save(r);
		return "Role saved";
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
	}

	@Override
	@Cacheable(value="Role",key="#id",condition="#id>0")
	public Role get(int id) {
		Session sess=em.unwrap(Session.class);
		return sess.get(Role.class, id);
	}

	@Override
	public Role update(Role r) {
		Session sess=em.unwrap(Session.class);
		sess.saveOrUpdate(r);
		return r;
	}
	
}