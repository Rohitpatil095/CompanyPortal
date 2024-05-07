package com.office.portal.employee.domain.dto;

import org.springframework.stereotype.Component;

import com.office.portal.employee.domain.entity.Employee;
import com.office.portal.employee.domain.entity.EmployeeLeave;
import com.office.portal.employee.infra.request.CreateEmployeeRequest;
import com.office.portal.employee.infra.request.EmployeeApplyLeaveRequest;
import com.office.portal.employee.infra.request.ExternalServiceEmployeeApplyLeaveRequest;
import com.office.portal.employee.infra.response.CreateEmployeeResponse;
import com.office.portal.employee.infra.response.EmployeeApplyLeaveResponse;
import com.office.portal.employee.infra.response.GetAllLeaveDetails;
import com.office.portal.employee.infra.response.SetErrorGenericResponse;

@Component
public class Dto {
	
	private CreateEmployeeRequest createEmployeeRequest;
	private CreateEmployeeResponse createEmployeeResponse;
	private Employee emp;
	private EmployeeApplyLeaveResponse employeeApplyLeaveResponse;
	private EmployeeApplyLeaveRequest employeeApplyLeaveRequest;
	private EmployeeLeave employeeLeave;
	private ExternalServiceEmployeeApplyLeaveRequest externalServiceEmployeeApplyLeaveRequest;
	private SetErrorGenericResponse setErrorCreateEmployeeResponse;
	private GetAllLeaveDetails getAllLeaveDetails;
	
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

	public EmployeeApplyLeaveResponse getEmployeeApplyLeaveResponse() {
		return employeeApplyLeaveResponse;
	}

	public void setEmployeeApplyLeaveResponse(EmployeeApplyLeaveResponse employeeApplyLeaveResponse) {
		this.employeeApplyLeaveResponse = employeeApplyLeaveResponse;
	}

	public EmployeeApplyLeaveRequest getEmployeeApplyLeaveRequest() {
		return employeeApplyLeaveRequest;
	}

	public void setEmployeeApplyLeaveRequest(EmployeeApplyLeaveRequest employeeApplyLeaveRequest) {
		this.employeeApplyLeaveRequest = employeeApplyLeaveRequest;
	}

	public EmployeeLeave getEmployeeLeave() {
		return employeeLeave;
	}

	public void setEmployeeLeave(EmployeeLeave employeeLeave) {
		this.employeeLeave = employeeLeave;
	}

	public ExternalServiceEmployeeApplyLeaveRequest getExternalServiceEmployeeApplyLeaveRequest() {
		return externalServiceEmployeeApplyLeaveRequest;
	}

	public void setExternalServiceEmployeeApplyLeaveRequest(
			ExternalServiceEmployeeApplyLeaveRequest externalServiceEmployeeApplyLeaveRequest) {
		this.externalServiceEmployeeApplyLeaveRequest = externalServiceEmployeeApplyLeaveRequest;
	}

	public SetErrorGenericResponse getSetErrorCreateEmployeeResponse() {
		return setErrorCreateEmployeeResponse;
	}

	public void setSetErrorCreateEmployeeResponse(SetErrorGenericResponse setErrorCreateEmployeeResponse) {
		this.setErrorCreateEmployeeResponse = setErrorCreateEmployeeResponse;
	}

	public GetAllLeaveDetails getGetAllLeaveDetails() {
		return getAllLeaveDetails;
	}

	public void setGetAllLeaveDetails(GetAllLeaveDetails getAllLeaveDetails) {
		this.getAllLeaveDetails = getAllLeaveDetails;
	}	
}
