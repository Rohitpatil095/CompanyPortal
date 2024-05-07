package com.office.portal.employee.businessservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.office.portal.employee.constants.EmployeeConstants;
import com.office.portal.employee.domain.dto.Dto;
import com.office.portal.employee.infra.Transformer;
import com.office.portal.employee.infra.impl.EmployeeApplyLeave;
import com.office.portal.employee.infra.impl.IDBEmployee;
import com.office.portal.employee.infra.request.ExternalServiceEmployeeApplyLeaveRequest;
import com.office.portal.employee.infra.response.EmployeeApplyLeaveResponse;
import com.office.portal.employee.infra.response.GetAllLeaveDetails;
import com.office.portal.employee.infra.response.SetErrorGenericResponse;

@Component
public class EmployeeApplyLeaveService {
	
	@Autowired private IDBEmployee idbEmployee;
	
	@Autowired private RestTemplate restTemplate;
	
	@Autowired private EmployeeApplyLeave employeeApplyLeave;
	
	@Autowired private Transformer transformer;
	@Autowired private SetErrorGenericResponse errResponse;
	@Autowired private HttpHeaders headers;
	@Autowired private EmployeeApplyLeaveResponse employeeApplyLeaveResponse;
	
	public boolean validateEmployee(Long id)
	{
		if(idbEmployee.getEmployeeByEmployeeId(id).getEmployee_Id()!=null)
		{
			 return true;
		}
		return false;
	}
	
	public Dto applyLeaveByEmployeeID(Dto dto)
	{

		Long empId=dto.getEmployeeApplyLeaveRequest().getId();
		if(this.validateEmployee(empId))
		{
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("leave_correlation_key", EmployeeConstants.LEAVE_COORELATION_KEY);
			
			transformer.transformEmployeeApplyLeaveRequestToExternalServiceEmployeeApplyLeaveRequest(dto);
			
			HttpEntity<ExternalServiceEmployeeApplyLeaveRequest> requestEntity= new HttpEntity<ExternalServiceEmployeeApplyLeaveRequest>(dto.getExternalServiceEmployeeApplyLeaveRequest(),headers);
			
			ResponseEntity<EmployeeApplyLeaveResponse> applyLeaveMicroserviceReponse= restTemplate.exchange(EmployeeConstants.LEAVE_SERVICE_URL_BUILDER+"/"+EmployeeConstants.LEAVE_SERVICE_URL_LEAVES_APPLYLEAVE,HttpMethod.POST,requestEntity,EmployeeApplyLeaveResponse.class);
//			EmployeeApplyLeaveResponse reponse= restTemplate.getForObject(EmployeeConstants.LEAVE_SERVICE_URL_SUBPART, EmployeeApplyLeaveResponse.class);

			if(applyLeaveMicroserviceReponse.getStatusCode()==HttpStatusCode.valueOf(200) && !applyLeaveMicroserviceReponse.getBody().getLeaveId().equals(null))
			{
				employeeApplyLeave.createNewEmployeeLeaveApplyRequest(dto);
				dto.setEmployeeApplyLeaveResponse(applyLeaveMicroserviceReponse.getBody());
				return dto;
			}
			else {
				errResponse.setErrorMessage("Error in leave creation calls..");
				dto.setSetErrorCreateEmployeeResponse(errResponse);
			}
		}
		errResponse.setErrorMessage("Trouble during validating employee..");
		dto.setSetErrorCreateEmployeeResponse(errResponse);
		return dto;
	}
	
	
	public List<String[]> employeeWithLeaveStatusPending(Dto dto)
	{
		List<String[]> emp=employeeApplyLeave.getAllEmployeeIdsWithLeaveStatusAsPending();
		List<String[]> notValidEmpList=new ArrayList<>();
		// add validation - check every entry in list contains array[] with 2 values empId, leaveId 
		if(emp.size()!=0)
		{		
			for(int i=0;i<emp.size();i++)
			{
				if(Long.parseLong(emp.get(i)[0])>=1 && Long.parseLong(emp.get(i)[1])>=1)
				{
					continue;
				}
				else {
					notValidEmpList.add(emp.get(i));
					emp.remove(i);
				}
			}
//			emp.stream().forEach(
//					
//			);
//			
//			( empl ->
//			{
//				if( empl.length!=2)
//				{
//					notValidEmpList.add(empl);
//				}
//			});
		}
		errResponse.setErrorMessage("employee with leave pending list is empty");
		dto.setSetErrorCreateEmployeeResponse(errResponse);
		return emp;
		
	}
	
	public Dto GetAllLeaveDetailsUsingLeaveId(Long leaveId,Dto dto)
	{
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("leave_correlation_key", EmployeeConstants.LEAVE_COORELATION_KEY);
		HttpEntity<GetAllLeaveDetails> requestEntity= new HttpEntity<GetAllLeaveDetails>(headers);
		
		ResponseEntity<GetAllLeaveDetails> getAllLeavesDetailsByLeaveId=restTemplate.exchange(EmployeeConstants.LEAVE_SERVICE_URL_BUILDER+"/"+EmployeeConstants.LEAVE_SERVICE_URL_LEAVES_GET_LEAVES_BY_LEAVE_ID+"/"+leaveId, HttpMethod.POST,requestEntity,GetAllLeaveDetails.class);
		
		if(getAllLeavesDetailsByLeaveId.getStatusCode()==HttpStatusCode.valueOf(200) && !getAllLeavesDetailsByLeaveId.getBody().getEmployeeRefrence().equals(null))
		{
			dto.setGetAllLeaveDetails(getAllLeavesDetailsByLeaveId.getBody());
			return dto;
		}
		else
		{
			errResponse.setErrorMessage("Failed to retrive leave details using leaveId");
			dto.setSetErrorCreateEmployeeResponse(errResponse);
		}
		errResponse.setErrorMessage("failed to make request to leaves service using leaveId");
		dto.setSetErrorCreateEmployeeResponse(errResponse);
		return dto;
	}

	
	public Dto GetEmployeeApplyLeaveDetailsForMailNotification(Long leaveId,Dto dto)
	{
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("leave_correlation_key", EmployeeConstants.LEAVE_COORELATION_KEY);
		HttpEntity<EmployeeApplyLeaveResponse> requestEntity= new HttpEntity<EmployeeApplyLeaveResponse>(headers);
		
		ResponseEntity<EmployeeApplyLeaveResponse> getAllLeavesDetailsByLeaveIdForMailNotification=restTemplate.exchange(EmployeeConstants.LEAVE_SERVICE_URL_BUILDER+"/"+EmployeeConstants.LEAVE_SERVICE_URL_LEAVES_GET_LEAVES_BY_LEAVE_ID+"/"+leaveId, HttpMethod.POST,requestEntity,EmployeeApplyLeaveResponse.class);
		
		if(getAllLeavesDetailsByLeaveIdForMailNotification.getStatusCode()==HttpStatusCode.valueOf(200) && !getAllLeavesDetailsByLeaveIdForMailNotification.getBody().getLeaveId().equals(null))
		{
			dto.setEmployeeApplyLeaveResponse(getAllLeavesDetailsByLeaveIdForMailNotification.getBody());
			return dto;
		}
		else
		{
			errResponse.setErrorMessage("Failed to retrive leave details using leaveId");
			dto.setSetErrorCreateEmployeeResponse(errResponse);
		}
		errResponse.setErrorMessage("failed to make request to leaves service using leaveId");
		dto.setSetErrorCreateEmployeeResponse(errResponse);
		return dto;
	}

}
