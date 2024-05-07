package com.office.portal.employee.infra.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.portal.employee.domain.dto.Dto;
import com.office.portal.employee.domain.entity.Employee;
import com.office.portal.employee.infra.repository.EmployeeRepository;
import com.office.portal.employee.infra.request.CreateEmployeeRequest;
import com.office.portal.employee.infra.response.CreateEmployeeResponse;

@Service
public class IDBEmployeeImpl implements IDBEmployee{
	
	private EmployeeRepository employeeRepo;
	private Dto dto;
	

	@Autowired
	public IDBEmployeeImpl(EmployeeRepository employeeRepo, Dto dto) {
		super();
		this.employeeRepo = employeeRepo;
		this.dto = dto;
	}

	@Override
	public Dto createNewEmployee(Dto dto) {
		Employee newEmployee= dto.getEmp();
		System.out.println("----------"+newEmployee.toString());
		employeeRepo.save(newEmployee);
		return dto;
	}

	@Override
	public List<CreateEmployeeResponse> getAllEmployee() {
		return null; // employeeRepo.findAll();
	}

	@Override
	public CreateEmployeeResponse getEmployeeByEmployeeId(Long empId) {
		dto.setEmp(employeeRepo.findById(empId).get());
		return dto.getCreateEmployeeResponse();
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

//	@Override
//	public List<int[]> employeeWatingForLeaveApproval() {
//		List<int[]> emp=employeeRepo.getEmpListPendingForLeaveApproval();
//		return emp;
//		{[35,7],[2,3]}
//		emp.stream()
//			.map(empl -> empl[0]!=0 && empl[1]!=0
//			).forEach(
//					
//					
//					 );	;
		
//	}
	

}
