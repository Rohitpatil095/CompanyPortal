package com.office.portal.employee.controller;

import java.net.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/leaves")
public class EmployeeLeavesController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	private HttpHeaders headers; 
		
	@PostMapping("/createLeave")
	public void applyEmployeeLeave()
	{
		restTemplate.exchange(null, null, null, null)
	}

}
