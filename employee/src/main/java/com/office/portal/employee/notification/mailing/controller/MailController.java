package com.office.portal.employee.notification.mailing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.office.portal.employee.notification.mailing.Mail;
import com.office.portal.employee.notification.mailing.service.MailExectorServiceImpl;

@RestController
public class MailController {

	@Autowired
	MailExectorServiceImpl exe;
	
    @PostMapping("/sendLeaveApprovalMail")
    public String
    sendMail(@RequestBody Mail mail)
    {
        String status
            = exe.sendSimpleMail(mail);
 
        return status;
    }
}
