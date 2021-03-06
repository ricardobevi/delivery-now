package com.ricardobevi.delivernow.gateways.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.ricardobevi.delivernow.dto.OrderDto;
import com.ricardobevi.delivernow.dto.RestaurantDto;
import com.ricardobevi.delivernow.gateways.MailGateway;

public class GmailMailGateway implements MailGateway {
	
	final String username = "username@gmail.com";
	final String password = "password";

	public void send(RestaurantDto restaurantDto, OrderDto orderDto) {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(username));
			
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(restaurantDto.getCommercialEmail()));
			
			message.setSubject("New Order!");
			
			message.setText("You have a new order! Address: " + orderDto.getAddress());

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	
	}
		


}
