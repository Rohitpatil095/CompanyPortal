package com.office.portal.employee.notification.mailing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.office.portal.employee.notification.mailing.Mail;

@Service
public class MailExectorServiceImpl{

	private JavaMailSender mailSender;

	@Autowired
	public MailExectorServiceImpl(JavaMailSender mailSender) {
		super();
		this.mailSender = mailSender;
	}
	
	@Value("${spring.mail.username}")
	private String sender;
	
	public String sendSimpleMail(Mail mail) {
		try {
			
			SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
			
			simpleMailMessage.setFrom(mail.getFrom());
			simpleMailMessage.setTo("somemail@gmail.com");
			simpleMailMessage.setText(mail.getMessage());
			simpleMailMessage.setSubject(mail.getSubject());
			
			mailSender.send(simpleMailMessage);
			return "mail send success";
		}
		catch (Exception e) {
			System.out.println("excep-------"+ e );
		}
		return "error in mail send process";
		
	}
}
