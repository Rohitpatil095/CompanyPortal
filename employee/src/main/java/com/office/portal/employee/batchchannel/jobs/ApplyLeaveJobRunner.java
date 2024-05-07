package com.office.portal.employee.batchchannel.jobs;

import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.office.portal.employee.domain.dto.Dto;

@Service
public class ApplyLeaveJobRunner {

	@Autowired private LeaveNotificationJobFactory leaveNotificationJobFactory;
	@Autowired private Dto dto;
	
	@Async
	public void run()
	{
		Job a= leaveNotificationJobFactory.leaveNotificationJobFactory(dto.getCreateEmployeeResponse());
		System.out.println("Job printing from async---- "+ a.toString() );
	}
}
