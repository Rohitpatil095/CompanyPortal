package com.office.portal.employee.infra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.office.portal.employee.domain.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//	@Query(nativeQuery = true,
//			value = "select emp_id,leave_id from employee_leave where status='pending'")
//	public List<int[]> getEmpListPendingForLeaveApproval();
}
