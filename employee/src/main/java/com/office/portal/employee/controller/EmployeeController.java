package com.office.portal.employee.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.office.portal.employee.domain.dto.Dto;
import com.office.portal.employee.infra.request.CreateEmployeeRequest;
import com.office.portal.employee.infra.response.CreateEmployeeResponse;

@RestController
public class EmployeeController {
	@PostMapping("/employee")
	public CreateEmployeeResponse req(@RequestBody CreateEmployeeRequest Employee)
	{
		System.out.println(Employee.getEmployee_Name());
		Employee.getEmployee_Address();
		
	}

}
