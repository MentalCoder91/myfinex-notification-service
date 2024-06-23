package com.expleo.notification_service.service;

import com.expleo.notification_service.dto.EmailDetails;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService{

	String sendSimpleMail(EmailDetails details, SimpleMailMessage mailMessage);

}
	