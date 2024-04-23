package com.office.portal.employee.infra.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.portal.employee.domain.dto.Dto;
import com.office.portal.employee.domain.entity.Employee;
import com.office.portal.employee.infra.Transformer;
import com.office.portal.employee.infra.repository.EmployeeRepository;
import com.office.portal.employee.infra.request.CreateEmployeeRequest;
import com.office.portal.employee.infra.response.CreateEmployeeResponse;

@Service
public class IDBEmployeeImpl implements IDBEmployee{
	
	private EmployeeRepository employeeRepo;
	private Transformer dtoTransformer;
	
	@Autowired
	public IDBEmployeeImpl(EmployeeRepository employeeRepo, Transformer dtoTransformer) {
		this.employeeRepo = employeeRepo;
		this.dtoTransformer=dtoTransformer;
	}

	@Override
	public Dto createNewEmployee(Dto dto) {
		Employee newEmployee= dtoTransformer.createEmployee(dto).getEmp();
		System.out.println("----------"+newEmployee.toString());
		employeeRepo.save(newEmployee);
		return dto;
	}

	@Override
	public List<CreateEmployeeResponse> getAllEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CreateEmployeeResponse> getAllEmployeeByEmployeeId(Long empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateEmployeeRequest getEmployeeBySupervisor_EmpId(Long Supervisor_EmpId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateEmployeeResponse updateEmployee(Long empId, Dto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEmployee(Long empId) {
		// TODO Auto-generated method stub
		
	}
	

}
