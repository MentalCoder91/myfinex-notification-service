package com.expleo.notification_service.functions;

import java.util.function.Consumer;
import java.util.function.Function;

import com.expleo.notification_service.dto.EmailDetails;
import com.expleo.notification_service.dto.ExpenseMessageDto;
import com.expleo.notification_service.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MessageFunctions implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;

	private static final Logger log = LoggerFactory.getLogger(MessageFunctions.class);

	@Bean
	public Function<ExpenseMessageDto,ExpenseMessageDto> email() {

		return expenseMsgDto -> {
			log.info("Sending email with the details received" + expenseMsgDto.toString());

			EmailDetails emailDetails = new EmailDetails();
			try {
				emailDetails.setMsgBody(getEmailBody(expenseMsgDto));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			emailDetails.setRecipient("anish.alwekar@gmail.com");
			emailDetails.setSubject("MyFinEx Transaction Alert");

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			sendSimpleMail(emailDetails, mailMessage);
			return expenseMsgDto;
		};

	}



	private String getEmailBody(ExpenseMessageDto expenseMessageDto) throws InterruptedException {
		

		
		StringBuilder str = new StringBuilder();
		str.append("*********************** User Details ***************************"+ "\n");
		str.append(" The expense for userName: " + expenseMessageDto.userName() + "\n");
		str.append("The user email :" + expenseMessageDto.emailId() + "\n");
		str.append("The user phone :" + expenseMessageDto.phoneNumber() + "\n");
		str.append("*********************** Budget Details ***************************"+ "\n");
		str.append("The remaining budget for user :" + expenseMessageDto.remainingBudget() + "\n");
		str.append("*********************** Expense Details ***************************"+ "\n");
		str.append("The expense failed for category :" + expenseMessageDto.expenseCategory() + "\n");
		str.append("The expense description :" + expenseMessageDto.expenseDescription() + "\n");
		str.append("The expense amount :" + expenseMessageDto.expenseAmount()+ "\n");

		return str.toString();

	}

	@Override
	public String sendSimpleMail(EmailDetails details, SimpleMailMessage mailMessage) {

		try {

			mailMessage.setFrom(sender);
			mailMessage.setTo(details.getRecipient());
			mailMessage.setText(details.getMsgBody());
			mailMessage.setSubject(details.getSubject());
			javaMailSender.send(mailMessage);
		} catch (Exception e) {
			 return "Error while Sending Mail";
		}

		return "Mail Sent Successfully...";

	}

}
