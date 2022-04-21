package com.codelogic.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	private Long userId;
	private String fullName;
	private String email;
	private String mobile;
	private String password;
	private Boolean isEmailVerified;
	private String token;
}
