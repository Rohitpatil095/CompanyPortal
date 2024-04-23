package com.office.portal.employee.infra;

import org.springframework.stereotype.Component;

import com.office.portal.employee.domain.dto.Dto;
import com.office.portal.employee.domain.entity.Employee;

@Component
public class Transformer {

	public Dto createEmployee(Dto dto)
	{
//		System.out.println("priting dto -----"+dto.getEmp().toString());
		Employee employee= new Employee();
		dto.setEmp(employee);
		
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
//		dto.getEmp().setSupervisor_EmpId(Long.parseLong(dto.getCreateEmployeeRequest().getSupervisor_EmpId()));
		
		return dto;
		
	}
	
	public Dto createEmployeeResponse(Dto dto)
	{
		Employee emp= new Employee();
		dto.setEmp(emp);
		dto.getEmp().setEmployee_Name(dto.getCreateEmployeeResponse().getEmployee_Name());
		dto.getEmp().setEmployee_Id(dto.getCreateEmployeeResponse().getEmployee_Id());
		dto.getEmp().setSupervisor_Name(dto.getCreateEmployeeResponse().getSupervisor_Name());
		dto.getEmp().setSupervisor_Email(dto.getCreateEmployeeResponse().getSupervisor_Email());
		
		return dto;
		
	}
}
