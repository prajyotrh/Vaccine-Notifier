package com.codelogic.client.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.codelogic.client.model.Alert;
import com.codelogic.client.model.User;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private RestTemplate template;

	String url = "http://VACCINE-NOTIFIER-SERVICE/vaccine-notifier/";

	@GetMapping("/send-otp/{email}")
	public String sendOTP(@PathVariable("email") String email) {
		return template.getForObject(url + "/send-otp/"+email, String.class);
	}

	// verify OTP
	@GetMapping("/verify-otp/{email}/{otp}")
	public Boolean verifyOtp(@PathVariable("email") String email, @PathVariable("otp") Long otp) {

		return template.getForObject(url + "/verify-otp/"+email+"/"+otp, Boolean.class);

	}

	// add user
	@PostMapping("/add-user")
	public User addUser(@RequestBody User user) {

		return template.postForObject(url + "/add-user", user, User.class);
	}

	// login
	@PostMapping("/user-login")
	public User loginUser(@RequestBody User user) {
		User u = template.postForObject(url + "/user-login", user, User.class);
		System.out.println("User data is : " + user);
		return u;
	}

	// logout
	@PostMapping("/user-logout")
	public boolean logoutUser(@RequestBody User user) {
		return template.postForObject(url + "/user-logout", user, Boolean.class);
	}

	// add alert
	@PostMapping("/add-alert/{token}")
	public Alert addAlert(@RequestBody Alert alert, @PathVariable("token") String token) {

		return template.postForObject(url + "/add-alert/"+token, alert, Alert.class);

	}

	// get all alerts
	@GetMapping("/all-alerts/{email}/{token}")
	public Alert[] getAllAlerts(@PathVariable("email") String email, @PathVariable("token") String token) {

		ResponseEntity<Alert[]> response = template.getForEntity(url + "/all-alerts/"+email+"/"+token, Alert[].class);
		Alert[] alerts = response.getBody();
		return alerts;
	}

	@GetMapping("/all-users")
	public User[] getAllUsers() {

		ResponseEntity<User[]> response = template.getForEntity(url + "/all-users", User[].class);
		User[] users = response.getBody();
		return users;
	}

	// delete alert
	@DeleteMapping("/delete-alert/{token}/{alertId}")
	public boolean deleteAlert(@PathVariable("token") String token, @PathVariable("alertId") Long alertId) {
		try {
			template.delete(url + "/delete-alert/"+token +"/"+alertId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
