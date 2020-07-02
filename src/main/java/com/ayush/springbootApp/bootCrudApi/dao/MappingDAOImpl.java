package com.ayush.springbootApp.bootCrudApi.dao;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;

import org.hibernate.Session;
import org.hibernate.Transaction;
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
	private ProjectService prjService;
	
	@Autowired
	private EmployeeService empService; 
	
	ObjectMapper om=new ObjectMapper();
	
	@Override
	public String empPrjMapping(String mapData) {
		
		try{
			Session currSession= entityManager.unwrap(Session.class);
			JsonNode json= om.readValue(mapData, JsonNode.class);
			int prjId1=json.get("projectId").asInt();
			Project prj1=prjService.getProjectById(prjId1);
			Set<Project> prjs=new HashSet<Project>();
			prjs.add(prj1);
			
			JsonNode empArr=json.get("employees");
			System.out.println("Employee json array="+empArr.asText());
			for(Iterator<JsonNode> empIt=empArr.iterator();empIt.hasNext();)
			{
				Integer empId= empIt.next().asInt();
				System.out.println("Employee ID:"+empId);
				Employee emp=empService.get(empId);
				emp.setProject(prjs);
				currSession.save(emp);
			}
			return "Employee(s) Mapped!";
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
		//int empId=(int)id;
		//Employee emp1=empService.get(id);
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
		String seatsStr="",jsonString="";
		try{
			//using jackson for JSON manipulation
			//ObjectMapper om=new ObjectMapper();
			ObjectNode seatsNode=om.readValue(seats,ObjectNode.class);
			if(seatsNode.has("seats"))
			{
				seatsStr= seatsNode.get("seats").asText();
			}
			System.out.println("In DAO, seat string="+seatsStr);
		
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
					System.out.println("Return="+ret);
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
			System.out.println("Return JSON:"+jsonString);
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
			NativeQuery query=currSession.createSQLQuery("Delete from emp_project_mapping where project_id=:prjId and emp_id =:empId");
			query.setParameter("prjId", prjId1);
			JsonNode empArr=json.get("employees");
			System.out.println("Employee json array="+empArr.asText());
			for(Iterator<JsonNode> empIt=empArr.iterator();empIt.hasNext();)
			{
				Integer empId= empIt.next().asInt();
				System.out.println("Employee ID:"+empId);
				//Employee emp=empService.get(empId);
				query.setParameter("empId", empId);
				//emp.setProject(prjs);
				int res=query.executeUpdate();
				if(res!=1)
					throw new Exception();
			}
			return "Employee(s) Released!";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	
	
}
