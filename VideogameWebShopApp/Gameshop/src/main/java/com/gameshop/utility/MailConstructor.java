package com.gameshop.utility;

import java.util.Locale;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.gameshop.domain.Order;
import com.gameshop.domain.User;

@Component
public class MailConstructor {
	
	 @Autowired
	 private Environment env;
	 
	 @Autowired 
	 private TemplateEngine templateEngine;
	 
	 public SimpleMailMessage constructResetTokenEmail(String contextPath, Locale locale, String token, User user, String password) {
		 String url = contextPath + "/newUser?token="+token;
		 String message = "\nPlease clock on this link to verify your email and edit your perosnal information. Your password is: \n"+password;
		 SimpleMailMessage email = new SimpleMailMessage();
		 email.setTo(user.getEmail());
		 email.setSubject("Outlaw's Gameshop - New User");
		 email.setText(url+message);
		 email.setFrom(env.getProperty("support.email"));
		 return email;
	 }
	 
	 public MimeMessagePreparator constructorOrderConfirmationEMail (User user, Order order, Locale locale) {
		 Context context = new Context();
		 context.setVariable("order", order);
		 context.setVariable("user", user);
		 context.setVariable("cartItemList", order.getCartItemList());
		 String text = templateEngine.process("orderConfirmationEmailTemplate", context);
		 
		 MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			 @Override
			 public void prepare(MimeMessage mimeMessage) throws Exception {
				 MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				 email.setTo(user.getEmail());
				 email.setSubject("Order Confirmation - "+order.getId());
				 email.setText(text, true);
				 email.setFrom(new InternetAddress("ranca.skalice@gmail.com"));
				 
			 }
		 };
		 return messagePreparator;
		 
	 }
}
