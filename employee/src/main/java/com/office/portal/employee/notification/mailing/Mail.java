package com.office.portal.employee.notification.mailing;

import org.springframework.stereotype.Component;


public class Mail {
	
	String subject;
	String message;
	String from;
	
	public Mail(String subject, String message, String from) {
		super();
		this.subject = subject;
		this.message = message;
		this.from = from;
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
	
}
