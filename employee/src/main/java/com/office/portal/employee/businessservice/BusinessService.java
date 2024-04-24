package com.office.portal.employee.businessservice;

import org.springframework.stereotype.Service;

import com.office.portal.employee.domain.dto.Dto;
import com.office.portal.employee.infra.request.CreateEmployeeRequest;

@Service
public class BusinessService {
	
	public boolean isEmployeeSupervisorDetailsPresent(Dto dto)
	{
		CreateEmployeeRequest emp=dto.getCreateEmployeeRequest();
		if(emp.getSupervisor_Email().equals(null) && emp.getSupervisor_EmpId().equals(null))
		{
			return true;
		}
		return false;
	}

}
