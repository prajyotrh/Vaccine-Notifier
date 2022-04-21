package com.codelogic.service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codelogic.modal.Alert;
import com.codelogic.modal.OtpVerification;
import com.codelogic.modal.User;
import com.codelogic.repository.AlertInterface;
import com.codelogic.repository.OtpVerifyInterface;
import com.codelogic.repository.UserInterface;

@Service
public class UserService {
	
	@Autowired
	private UserInterface userInterface;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AlertInterface alertInterface;
	
	@Autowired
	private OtpVerifyInterface otpVerifyInterface;

	public String sendOTP(String email) {
		
		Random random = new Random();
		int number = random.nextInt(999999);
		String otp = String.format("%06d", number);
		
		OtpVerification otpVerification = otpVerifyInterface.getByEmail(email);
		
		if(otpVerification == null)
			otpVerification = new OtpVerification();
		
		otpVerification.setEmail(email);
		otpVerification.setOtp(otp);
		
		try {
			otpVerifyInterface.save(otpVerification);
			emailService.sendMail(email, otp);
			return otp;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Error occured while sending mail.";
	}
	
	public Boolean verifyOtp(String email, Long otp) {
		
		OtpVerification otpVerification = otpVerifyInterface.getByEmail(email);
		
		if(otpVerification == null)
			return false;
		
		if(Integer.parseInt(otpVerification.getOtp()) == otp) {
			return true;
		}
		
		return false;
	}

	public User addUser(User user) {
		user.setToken(UUID.randomUUID().toString());
		return userInterface.save(user);
	}
	
	public User loginUser(User user) {
		User tempUser = userInterface.findByEmail(user.getEmail());
		
		System.out.println("Login request for user in service : " + tempUser);
		
		if(tempUser == null) {
			return null;
		}
		System.out.println("tempuser : "+tempUser);
		if(tempUser.getEmail().equalsIgnoreCase(user.getEmail()) && 
				tempUser.getPassword().equals(user.getPassword())) {
			tempUser.setToken(UUID.randomUUID().toString());
			userInterface.save(tempUser);
			tempUser.setPassword("");
			return tempUser;
		}
		
		return null;
	}

	public boolean isUserLoggedIn(String email, String token) {
		User user = userInterface.findByEmail(email);
		if(user != null && user.getToken().equals(token)) {
			return true;
		}
 		return false;
	}
	
	public List<User> getAllUsers() {
		return userInterface.findAll();
	}

	public Alert addAlert(Alert alert) {
		
		List<Alert> allNotofiers = getAllAlerts(alert.getEmail());
		
		for(Alert i : allNotofiers) {
			if(i.getLocationId()== alert.getLocationId()) {
				return i;
			}
		}
		
		return alertInterface.save(alert);
	}
	
	public List<Alert> getAllAlerts(String email) {
		return alertInterface.findAllByEmail(email);
	}

	public boolean deleteAlert(Long alertId) {
		try {
			alertInterface.deleteById(alertId);
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean logout(User user) {
		User userObj = userInterface.findByEmail(user.getEmail());
		
		if(userObj != null) {
			userObj.setToken("");
			userInterface.save(userObj);
			return true;
		}
		
		return false;
	}

}
