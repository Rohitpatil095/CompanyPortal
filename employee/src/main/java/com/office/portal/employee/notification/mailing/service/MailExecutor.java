package com.office.portal.employee.notification.mailing.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.office.portal.employee.notification.mailing.Mail;

public interface MailExecutor {
	String sendSimpleMail(Mail mail);
}
