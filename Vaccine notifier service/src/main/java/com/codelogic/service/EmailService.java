package com.codelogic.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.codelogic.modal.Location;

@Service
public class EmailService {
	
	@Value("${spring.mail.username}")
	String adminMail;
	
	@Value("${spring.mail.password}")
	String adminPassword;
	


	public boolean sendMail(String email, String otp) throws AddressException, MessagingException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");


		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(adminMail, adminPassword);
			}
		});
		
		
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(adminMail, false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		msg.setSubject("Email Verification");
		msg.setContent("Email Verification", "text");
		msg.setSentDate(new Date());

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent("Verify your mail. Verification OTP is "+ otp, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		msg.setContent(multipart);

		try {
			Transport.send(msg);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean sendVaccineNotification(String email, Location location) throws MessagingException {
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(adminMail, adminPassword);
			}
		});
		
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(adminMail, false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
		msg.setSubject("Vaccine available notification");
		msg.setContent("Vaccine available notification", "text");
		msg.setSentDate(new Date());

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		Object msgContent = "Vaccine is available at "+location.getLocationName()+".<br> Vaccine name :"+location.getVaccineName()+".<br> Vaccine type : "+location.getVaccineType();
		messageBodyPart.setContent(msgContent, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		msg.setContent(multipart);

		try {
			Transport.send(msg);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}

	
	
	
}
