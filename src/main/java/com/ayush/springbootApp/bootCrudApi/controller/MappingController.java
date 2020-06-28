package com.ayush.springbootApp.bootCrudApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayush.springbootApp.bootCrudApi.dao.EmployeeDAOImpl;
import com.ayush.springbootApp.bootCrudApi.service.MappingService;

@RestController
@RequestMapping("/mapping")
public class MappingController{
	
	@Autowired
	private MappingService mappingService;
	
	@PostMapping("/empPrj")
	public String empPrjMapping(@RequestBody String mapData){
		return mappingService.empPrjMapping(mapData);
	}
	
	@GetMapping("/empWorkstation/{empId}/{seatNo}")
	public String empWorkStationMaping(@PathVariable("empId") int empId,@PathVariable("seatNo") String seatNo) 
	{
		mappingService.empWorkStationMaping(empId,seatNo);
		return "Workstation set!";
	}
	
	@PutMapping("/markSeatsVacant")
	public String markAsVacant(@RequestBody String seats)
	{
		System.out.println("Seats str="+seats);
		return mappingService.markAsVacant(seats);
	}
}