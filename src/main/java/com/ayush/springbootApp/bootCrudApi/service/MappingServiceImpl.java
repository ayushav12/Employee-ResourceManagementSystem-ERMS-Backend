package com.ayush.springbootApp.bootCrudApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayush.springbootApp.bootCrudApi.dao.MappingDAO;
import com.ayush.springbootApp.bootCrudApi.model.Employee;

@Service
public class MappingServiceImpl implements MappingService{
	
	@Autowired
	private MappingDAO MappingDAO;
	
	@Transactional
	@Override
	public String empPrjMapping(String mapData) {
		
		return MappingDAO.empPrjMapping(mapData);
		
	}

	@Transactional
	@Override
	public void empWorkStationMaping(int empId,String seatNo) {
		MappingDAO.empWorkStationMaping(empId,seatNo);
	}
	
	@Transactional
	@Override
	public String markAsVacant(String seats) {
		return MappingDAO.markAsVacant(seats);
	}
	
}