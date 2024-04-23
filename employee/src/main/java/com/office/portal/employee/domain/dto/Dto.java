package com.office.portal.employee.domain.dto;

import com.office.portal.employee.domain.entity.Employee;
import com.office.portal.employee.infra.request.CreateEmployeeRequest;
import com.office.portal.employee.infra.response.CreateEmployeeResponse;

public class Dto {
	
	public CreateEmployeeRequest createEmployeeRequest;
	public CreateEmployeeResponse createEmployeeResponse;
	public Employee emp;
	
	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public CreateEmployeeRequest getCreateEmployeeRequest() {
		return createEmployeeRequest;
	}
	
	public void setCreateEmployeeRequest(CreateEmployeeRequest createEmployeeRequest) {
		this.createEmployeeRequest = createEmployeeRequest;
	}
	
	public CreateEmployeeResponse getCreateEmployeeResponse() {
		return createEmployeeResponse;
	}
	
	public void setCreateEmployeeResponse(CreateEmployeeResponse createEmployeeResponse) {
		this.createEmployeeResponse = createEmployeeResponse;
	} 
	
	

}
