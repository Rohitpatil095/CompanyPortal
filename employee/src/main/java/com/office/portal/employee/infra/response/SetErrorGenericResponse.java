package com.office.portal.employee.infra.response;

public class SetErrorGenericResponse {

	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	
	public SetErrorGenericResponse() {
		super();
	}

	public SetErrorGenericResponse(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	
	
}
