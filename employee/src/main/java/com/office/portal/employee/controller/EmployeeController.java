package com.office.portal.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.office.portal.employee.businessservice.CreateEmployeeBusinessService;
import com.office.portal.employee.domain.dto.Dto;
import com.office.portal.employee.infra.request.CreateEmployeeRequest;
import com.office.portal.employee.infra.response.CreateEmployeeResponse;

@RestController
public class EmployeeController {
	
	private CreateEmployeeBusinessService createEmployeeBusinessService;
	private Dto dto;
	
	@Autowired
	public EmployeeController(CreateEmployeeBusinessService svc,Dto dto) {
		super();
		this.createEmployeeBusinessService = svc;
		this.dto =dto;
	}


	@PostMapping("/employee")
	public CreateEmployeeResponse createNewEmployee(@RequestBody CreateEmployeeRequest Employee)
	{
		dto.setCreateEmployeeRequest(Employee);
		createEmployeeBusinessService.createEmployeeService(dto);
		return dto.getCreateEmployeeResponse();
	}
	
	@GetMapping("/getAllEmployees")
	public List<CreateEmployeeResponse> getAllEmployee(){
		return null;
	}
	
	@GetMapping("/getEmployeeByEmployeeId/{id}")
	public CreateEmployeeResponse getEmployeeByEmployeeId(@PathVariable("id") Long id){
//		createEmployeeBusinessService.createEmployeeService(id, dto);
		createEmployeeBusinessService.getEmployeeByIdService(id, dto);
//		dto.getCreateEmployeeResponse();
		return dto.getCreateEmployeeResponse();
	}
	
//	@GetMapping("/getAllEmplIdForLeaveStatusPending")
//	public List<Long> GetAllEmplIdForLeaveStatusPending()
//	{
//		
//	}
}
