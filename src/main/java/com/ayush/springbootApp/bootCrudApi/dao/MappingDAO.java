package  com.ayush.springbootApp.bootCrudApi.dao;

import com.ayush.springbootApp.bootCrudApi.model.Employee;

public interface MappingDAO{
	
	public String empPrjMapping(String mapData);
	
	public Employee empWorkStationMaping(int empId,String seatNo);
	
	public String markAsVacant(String seats);
	
	public String releaseEmpFromProject(String relData);
}