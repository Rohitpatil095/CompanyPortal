package com.office.portal.employee.batchchannel.jobs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.office.portal.employee.businessservice.CreateEmployeeBusinessService;
import com.office.portal.employee.businessservice.EmployeeApplyLeaveService;
import com.office.portal.employee.constants.EmployeeConstants;
import com.office.portal.employee.domain.dto.Dto;
import com.office.portal.employee.infra.response.CreateEmployeeResponse;
import com.office.portal.employee.notification.mailing.Mail;
import com.office.portal.employee.notification.mailing.service.MailExecutor;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class LeaveNotificationJobFactory {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private EmployeeApplyLeaveService employeeApplyLeaveService;
	
	@Autowired
	private MailExecutor mailExecutor;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired 
	private CreateEmployeeBusinessService createEmployeeBusinessService;
	
	@Autowired
	private CreateEmployeeResponse createEmployeeResponse;
	
	@Autowired 
	private Dto dto;
	
	@Autowired
	private Mail mail;
	
	private String htmlContentFormated=null;
	private String contextTemplate=null;
	
	private MimeMessage message =mailSender.createMimeMessage();
	
	public Job leaveNotificationJobFactory(CreateEmployeeResponse createEmployeeResponse)
	{
		return jobBuilderFactory.get("leaveNotificationJobFactory")
		.start(leaveNotificationStepfactory(createEmployeeResponse))
//		.next(spfEvaluationTasklet())
		.build();
	}
	
	private Step leaveNotificationStepfactory(CreateEmployeeResponse createEmployeeResponse)
	{
		return stepBuilderFactory.get("leaveNotificationStepFactory")
		.tasklet(mailContextReaderTasklet(dto))
		.tasklet(SetLeaveNotificationOverMailTasklet(dto,mail,createEmployeeResponse))
		.tasklet(FomatLeaveNotificationMailContextTasklet(contextTemplate,dto))
		.build();
	}


	private Tasklet SetLeaveNotificationOverMailTasklet(Dto dto, Mail mail, CreateEmployeeResponse createEmployeeResponse)
	{
		return new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				
//				mail.setSubject("Leave Approval Pending Status for Empid- " + + "and LeaveId- "+ );
				mail.setSubject("Leave Approval Pending Status Notification Mail");
				mail.setMessage(htmlContentFormated);
				
				try {
					message.setFrom(mail.getFrom());
					message.setRecipients(MimeMessage.RecipientType.TO,createEmployeeResponse.getSupervisor_Email());
					message.setContent(mail.getMessage(),"text/html; charset=utf-8");
					message.setSubject(mail.getSubject());
					mailExecutor.mimeLeaveMailNotification(message);
					
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(message.getAllRecipients().length!=0)
				{
					return RepeatStatus.FINISHED;
				}
				// need some proper exception handling mechanism
				return RepeatStatus.CONTINUABLE;
			}
		};
	}


		

	private Tasklet mailContextReaderTasklet(Dto dto)
	{
		return new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				// TODO Auto-generated method stub
				Path path= Paths.get(EmployeeConstants.LEAVE_APPROVAL_NOTIFICATION_MAIL_CONTEXT_FILE_PATH);
				Tasklet tasklet=null;
				
				try 
				{
					contextTemplate = Files.readString(path);
					if(contextTemplate.isEmpty())
					{
						// raise some exception 
						System.out.println("exception due to contextTemplate is empty - from");
					}	
					else
					{
						tasklet= FomatLeaveNotificationMailContextTasklet(contextTemplate,dto);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
//					return RepeatStatus.CONTINUABLE;
				}
				
				if(tasklet.equals(RepeatStatus.CONTINUABLE))
				{
					//throw exception 
					System.out.println("error in FomatLeaveNotificationMailContextTasklet task");
				}
				return RepeatStatus.FINISHED;
			}
		};
	}
				
	
	private Tasklet FomatLeaveNotificationMailContextTasklet(String contextTemplate,Dto dto)
	{
		return new Tasklet()
		{

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				
				htmlContentFormated=contextTemplate.replace("{empRef}",dto.getGetAllLeaveDetails().getEmployeeRefrence());
				htmlContentFormated.replace("{leaveFrom}",dto.getGetAllLeaveDetails().getAppliedLeaveFrom().toString());
				htmlContentFormated.replace("{leaveTo}",dto.getGetAllLeaveDetails().getAppliedLeaveTo().toString());
				
				
				if(!htmlContentFormated.isEmpty())
				{
					return RepeatStatus.FINISHED;
				}
				return RepeatStatus.CONTINUABLE;
			}
		};
	}


	
// thinks so not req. implement this logic in employee getLeaves side
//	private boolean isMailApprovalInPendingStateValidatorTasklet(Dto dto)
//	{
//		return true;
//	}
	
	private boolean isSenderInCompaniesWhiteListedMailsaTasklet()
	{
		return true;
	}
	
	private boolean validateReceiverDomainTasklet()
	{
		return true;
	}
	
	private Step spfEvaluationTasklet()
	{
		// implement dnslookup,verify is mail send by comp/dns whitelisted ip, etc...
		return null;
	}
}
