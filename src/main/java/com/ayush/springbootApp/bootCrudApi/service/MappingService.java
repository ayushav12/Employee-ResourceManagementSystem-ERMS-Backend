package com.ayush.springbootApp.bootCrudApi.service;

public interface MappingService{
	
	public String empPrjMapping(String mapData);
	
	public void empWorkStationMaping(int empId,String seatNo);
	
	public String markAsVacant(String seats);
	
	public String releaseEmpFromProject(String relData);
}