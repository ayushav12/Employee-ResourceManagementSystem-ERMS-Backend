package com.ayush.springbootApp.bootCrudApi.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ayush.springbootApp.bootCrudApi.model.*;
import com.ayush.springbootApp.bootCrudApi.service.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Repository
public class MappingDAOImpl implements MappingDAO{

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private ProjectService prjService;
	
	@Autowired
	private RoleService roleService;
	
	ObjectMapper om=new ObjectMapper();
	
	@Override
	public String empPrjMapping(String mapData) {
		
		try{
			Session currSession= entityManager.unwrap(Session.class);
			String query="select count(*) from EmployeeProjectRole epr where epr.emp.id=:empId and epr.prj.id=:prjId and epr.role.id=:roleId";
			Query hql= currSession.createQuery(query);
			
			//JsonNode is immutable whereas its subclass ObjectNode is immutable.Hence, when reading JSON should use JsonNode
			//and when manipulating JSON,should use ObjectNode
			JsonNode json= om.readValue(mapData, JsonNode.class);
			int prjId1=json.get("projectId").asInt();
			
			hql.setParameter("prjId", prjId1);
			//JsonNode empArr=json.get("employees"); //this is also fetch same result as path("employees")
			JsonNode empArr=json.path("employees");
			if(empArr.isArray())
			{
				for(JsonNode empObj:empArr)
				{
					EmployeeProjectRole epr=new EmployeeProjectRole();
					epr.setPrj(prjService.getProjectById(prjId1));
					
					Integer empId= empObj.path("empId").asInt();
					epr.setEmp(empService.get(empId));
					hql.setParameter("empId", empId);
					
					Integer roleId= empObj.path("roleId").asInt();
					epr.setRole(roleService.get(roleId));
					hql.setParameter("roleId", roleId);
					
					//check through criteria API to facilitate single role of an employee in a project by getting count from table
					/*CriteriaBuilder cb=currSession.getCriteriaBuilder(); 
					CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
					criteria.select(cb.count(criteria.from(EmployeeProjectRole.class)));
					
					
					Root<EmployeeProjectRole> eprRoot= criteria.from(EmployeeProjectRole.class);
	                //criteria.select(eprRoot);
					
	                List<Predicate> prds=new ArrayList<>();
	                prds.add(cb.equal(eprRoot.get("emp_id"),empId));
	                prds.add(cb.equal(eprRoot.get("prj_id"),prjId1));
	                prds.add(cb.equal(eprRoot.get("role_id"),roleId));

	                criteria.where(prds.toArray(new Predicate[]{})); //select query
	                Long count= entityManager.createQuery(criteria).getSingleResult();*/
					Long count=(Long) hql.getSingleResult();
	                 
	                if(count==0)
	                	currSession.save(epr);
				}
				return "Employee(s) Mapped!";
			}
			else{
				return "Incorrect Data formatting!";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return e.getMessage();
		}
		
	}

	@Override
	public Employee empWorkStationMaping(int empId,String seatNo) {
		Session currSession= entityManager.unwrap(Session.class);
		Employee emp=empService.get(empId);
		String hql1="FROM Workstation uw WHERE uw.seat_no = :sno"; //in HQL,type(model class name) is used instead of table name.Therefore,using model name Workstation instead of table name user_workstation
		Query query= currSession.createQuery(hql1);
		query.setParameter("sno",seatNo);
		//Workstation ws1=currSession.get(Workstation.class, seatNo);
		Workstation ws1=(Workstation)query.getSingleResult();
		ws1.setEmp(emp);
		return emp;
	}
	
	@Override
	public String markAsVacant(String seats)
	{
		String seatsStr="";
		String jsonString="";
		try{
			//using jackson for JSON manipulation
			//ObjectMapper om=new ObjectMapper();
			ObjectNode seatsNode=om.readValue(seats,ObjectNode.class);
			if(seatsNode.has("seats"))
			{
				seatsStr= seatsNode.get("seats").asText();
			}
			
			String hql2="update Workstation uw set uw.emp=null where id = :seatId";
			Query query=entityManager.unwrap(Session.class).createQuery(hql2);
			String[] seatIds= seatsStr.split(",");
			
			JsonNode rootNode=om.createObjectNode();
			
			for(String seatId: seatIds)
			{
				if("".equalsIgnoreCase(seatId) || seatId==null)
				{
					break;
				}
				else
				{
					Integer intSeatId=Integer.valueOf(seatId);
					int ret=query.setParameter("seatId",intSeatId).executeUpdate();
					JsonNode childNode=om.createObjectNode();
					((ObjectNode) childNode).put("SeatId",seatId);
					if(ret==1)
						((ObjectNode) childNode).put("Vacant","Yes");
					else
						((ObjectNode) childNode).put("Vacant","No");
					
					((ObjectNode) rootNode).set("Seat", childNode);
				}
			}
			jsonString = om.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return jsonString;
	}

	@Override
	public String releaseEmpFromProject(String relData) {
		Session currSession= entityManager.unwrap(Session.class);
		try{
			entityManager.flush(); //to write changes to DB from persistent context
			entityManager.clear(); //to clear persistence context
			JsonNode json= om.readValue(relData, JsonNode.class);
			int prjId1=json.get("projectId").asInt();
			
			@SuppressWarnings("rawtypes")
			NativeQuery query=currSession.createSQLQuery("Delete from employee_project_role where prj_id=:prjId and emp_id =:empId and role_id=:roleId");
			query.setParameter("prjId", prjId1);
			JsonNode empArr=json.get("employees");
			
			if(empArr.isArray())
			{
				for(JsonNode empObj:empArr)
				{
					Integer empId= empObj.path("empId").asInt();
					
					Integer roleId=empObj.path("roleId").asInt();
					
					query.setParameter("empId", empId);
					query.setParameter("roleId", roleId);
					
					int res=query.executeUpdate();
					if(res!=1)
						throw new Exception();
				}
				return "Employee(s) Released!";
			}
			else
			{
				return "Incorrect data formatting";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	
	
}
