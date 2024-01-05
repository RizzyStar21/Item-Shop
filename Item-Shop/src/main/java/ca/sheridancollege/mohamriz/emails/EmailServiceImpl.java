package ca.sheridancollege.mohamriz.emails;

import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import ca.sheridancollege.mohamriz.beans.Store;

@Component
public class EmailServiceImpl {

	@Autowired
	private JavaMailSender emailSender;
	
	public void sendSimpleMessage(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		emailSender.send(message);
	}
	
	public void sendSpam(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		int i = 0;
		while (i < 10) {
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		emailSender.send(message);
		}
	}
	
	@Autowired
	private TemplateEngine templateEngine;
	

	public void sendMailWithThymeleaf(String to, String subject, String name,
			ArrayList<Store> items, String footer) 
			throws MessagingException {
		
		//Import from thymeleaf
		final Context ctx = new Context();
		ctx.setVariable("name", name);
		ctx.setVariable("items", items);
		/*
		 * ctx.setVariable("cName", cName); ctx.setVariable("num", num);
		 * ctx.setVariable("address", address); ctx.setVariable("email", email);
		 */
		ctx.setVariable("footer", footer);
		
		//adding contecxt varaibles into html
		final String htmlContext = this.templateEngine.process("email.html", ctx);
		
		final MimeMessage mimeMessage = this.emailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		
		//construct parts of the email
		message.setTo(to);
		message.setSubject(subject);
		message.setText(htmlContext, true);//true will process as HTML
		
		this.emailSender.send(mimeMessage);
	}
}
