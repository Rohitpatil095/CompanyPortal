package com.office.portal.employee.batchchannel.jobs.schedulers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.office.portal.employee.batchchannel.jobs.ApplyLeaveJobRunner;
import com.office.portal.employee.batchchannel.jobs.LeaveNotificationJobFactory;
import com.office.portal.employee.businessservice.CreateEmployeeBusinessService;
import com.office.portal.employee.businessservice.EmployeeApplyLeaveService;
import com.office.portal.employee.domain.dto.Dto;
import com.office.portal.employee.infra.impl.EmployeeApplyLeave;
import com.office.portal.employee.infra.response.CreateEmployeeResponse;

@Service
public class EmployeeApplyLeaveNotificationScheduler {
	
	private EmployeeApplyLeave employeeApplyLeave;
	private Dto dto;

	@Autowired
	private EmployeeApplyLeaveService employeeApplyLeaveService;

	@Autowired 
	private CreateEmployeeBusinessService createEmployeeBusinessService;
	
	@Autowired
	private CreateEmployeeResponse createEmployeeResponse;
	
	@Autowired
	private LeaveNotificationJobFactory leaveNotificationJobFactory;
	
	@Autowired
	private ApplyLeaveJobRunner applyLeaveJobRunner;
	
	@Autowired
	public EmployeeApplyLeaveNotificationScheduler(EmployeeApplyLeave employeeApplyLeave, Dto dto) {
		super();
		this.employeeApplyLeave = employeeApplyLeave;
		this.dto=dto;
	}
	
//	@Scheduled(cron = "0 0 10 1/1 * ? *")
	@Scheduled(cron = "0 0/1 * 1/1 * ? *")
	public  void sendLeaveNotificationExecutor(Dto dto)
	{
		List<String[]> employeeWithLeaveIds=employeeApplyLeaveService.employeeWithLeaveStatusPending(dto);
		System.out.println("executed scheduler");
		for(int i=0;i<employeeWithLeaveIds.size();i++)
		{
			dto =employeeApplyLeaveService.GetAllLeaveDetailsUsingLeaveId(Long.parseLong(employeeWithLeaveIds.get(i)[1]) ,dto);
			dto= createEmployeeBusinessService.getEmployeeByIdService(Long.parseLong(employeeWithLeaveIds.get(i)[0]), dto);
			dto=employeeApplyLeaveService.GetEmployeeApplyLeaveDetailsForMailNotification(dto.getGetAllLeaveDetails().getLeaveId(), dto);
			// call leavenotification job factory which send mail
			createEmployeeResponse= dto.getCreateEmployeeResponse();
			if(createEmployeeResponse.getSupervisor_Email().equals(null))
			{
				// throw excepton
			}
			else
			{
//				call job to send mail notification
				applyLeaveJobRunner.run();
			}
		}
	}
}
