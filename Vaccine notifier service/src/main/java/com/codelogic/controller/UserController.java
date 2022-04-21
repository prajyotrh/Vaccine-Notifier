package com.codelogic.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codelogic.modal.Alert;
import com.codelogic.modal.User;
import com.codelogic.service.UserService;

@RestController
@RequestMapping("/vaccine-notifier")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;

	// send OTP while verification of email.
	@GetMapping("/send-otp/{email}")
	public String sendOTP(@PathVariable("email") String email) {
		return userService.sendOTP(email);
	}

	// verify OTP
	@GetMapping("/verify-otp/{email}/{otp}")
	public Boolean verifyOtp(@PathVariable("email") String email, @PathVariable("otp") Long otp) {

		return userService.verifyOtp(email, otp);

	}

	// add user
	@PostMapping("/add-user")
	public User addUser(@RequestBody User user) {

		if (user.getIsEmailVerified() == true) {
			System.out.println("USer data " + user);
			return userService.addUser(user);
		}
		return new User();
	}

	// login
	@PostMapping("/user-login")
	public User loginUser(@RequestBody User user) {
		System.out.println("Login request for user in controller : " + user.getEmail());
		return userService.loginUser(user);
	}

	// logout
	@PostMapping("/user-logout")
	public boolean logoutUser(@RequestBody User user) {
		if (userService.isUserLoggedIn(user.getEmail(), user.getToken())) {
			return userService.logout(user);
		}
		return false;
	}

	// add alert
	@PostMapping("/add-alert/{token}")
	public Alert addAlert(@RequestBody Alert alert, @PathVariable("token") String token) {

		if (userService.isUserLoggedIn(alert.getEmail(), token)) {
			return userService.addAlert(alert);
		}

		return new Alert();

	}

	// get all alerts
	@GetMapping("/all-alerts/{email}/{token}")
	public List<Alert> getAllAlerts(@PathVariable String email, @PathVariable String token) {
		if (userService.isUserLoggedIn(email, token)) {
			return userService.getAllAlerts(email);
		}
		return Collections.emptyList();
	}
	
	// get all users
	@GetMapping("/all-users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	// delete alert
	@DeleteMapping("/delete-alert/{token}/{alertId}")
	public boolean deleteAlert(@PathVariable("token") String token, @PathVariable("alertId") Long alertId) {
		return userService.deleteAlert(alertId);
	}
}
