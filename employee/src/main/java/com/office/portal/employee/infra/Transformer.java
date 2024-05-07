package com.office.portal.employee.infra;

import org.springframework.stereotype.Component;

import com.office.portal.employee.domain.dto.Dto;
import com.office.portal.employee.domain.entity.Employee;
import com.office.portal.employee.infra.request.ExternalServiceEmployeeApplyLeaveRequest;
import com.office.portal.employee.infra.response.CreateEmployeeResponse;
import com.office.portal.employee.infra.response.SetErrorGenericResponse;

@Component
public class Transformer {

	public Dto createEmployee(Dto dto)
	{
//		System.out.println("priting dto -----"+dto.getEmp().toString());
		Employee employee= new Employee();
		dto.setEmp(employee);
		
//		dto.getEmp().setEmployee_ref(dto.getCreateEmployeeRequest().getEmployee_Name()+);
		dto.getEmp().setAadhar_No(dto.getCreateEmployeeRequest().getAadhar_No());
		dto.getEmp().setBlood_Group(dto.getCreateEmployeeRequest().getBlood_Group());
		dto.getEmp().setContact(dto.getCreateEmployeeRequest().getContact());
		dto.getEmp().setSec_Contact(dto.getCreateEmployeeRequest().getSec_Contact());
		dto.getEmp().setEmployee_Address(dto.getCreateEmployeeRequest().getEmployee_Address());
		dto.getEmp().setEmployee_Name(dto.getCreateEmployeeRequest().getEmployee_Name());
		dto.getEmp().setPan_No(dto.getCreateEmployeeRequest().getPan_No());
		dto.getEmp().setPassport_No(dto.getCreateEmployeeRequest().getPassport_No());
		dto.getEmp().setSupervisor_Email(dto.getCreateEmployeeRequest().getSupervisor_Email());
		dto.getEmp().setSupervisor_Name(dto.getCreateEmployeeRequest().getSupervisor_Name());
		dto.getEmp().setSupervisor_EmpId(Long.parseLong(dto.getCreateEmployeeRequest().getSupervisor_EmpId()));
		
		return dto;
		
	}
	
	public Dto createEmployeeResponseTransformer(Dto dto, Boolean isSuperVisorDetailsPresent)
	{
		if(isSuperVisorDetailsPresent) {
			SetErrorGenericResponse setErrorCreateEmployeeResponse=new SetErrorGenericResponse("Supervisor details not present");
			dto.setSetErrorCreateEmployeeResponse(setErrorCreateEmployeeResponse);
			return dto;
		}
		CreateEmployeeResponse createEmployeeResponse= new CreateEmployeeResponse();
		dto.setCreateEmployeeResponse(createEmployeeResponse);
		
		Employee emp= dto.getEmp();
		dto.setEmp(emp);
		
		dto.getCreateEmployeeResponse().setEmployee_Name(emp.getEmployee_Name());
		dto.getCreateEmployeeResponse().setEmployee_Id(emp.getEmployee_Id());
		dto.getCreateEmployeeResponse().setSupervisor_Name(emp.getSupervisor_Name());
		dto.getCreateEmployeeResponse().setSupervisor_Email(emp.getSupervisor_Email());
		
		return dto;
		
	}
	
	public Dto transformEmployeeApplyLeaveRequestToExternalServiceEmployeeApplyLeaveRequest(Dto dto)
	{
		ExternalServiceEmployeeApplyLeaveRequest externalServiceEmployeeApplyLeaveRequest= new ExternalServiceEmployeeApplyLeaveRequest();
		dto.setExternalServiceEmployeeApplyLeaveRequest(externalServiceEmployeeApplyLeaveRequest);

		dto.getExternalServiceEmployeeApplyLeaveRequest().setEmployeeRef(dto.getEmployeeApplyLeaveRequest().getEmployeeName()+dto.getEmployeeApplyLeaveRequest().getId());
		dto.getExternalServiceEmployeeApplyLeaveRequest().setFromDate(dto.getEmployeeApplyLeaveRequest().getFromDate());
		dto.getExternalServiceEmployeeApplyLeaveRequest().setToDate(dto.getEmployeeApplyLeaveRequest().getToDate());
		dto.getExternalServiceEmployeeApplyLeaveRequest().setLeaveType(dto.getEmployeeApplyLeaveRequest().getLeaveType());
		dto.getExternalServiceEmployeeApplyLeaveRequest().setNumberOfLeaves(dto.getEmployeeApplyLeaveRequest().getNumberOfLeaves());
	
		return dto;
	}
}
