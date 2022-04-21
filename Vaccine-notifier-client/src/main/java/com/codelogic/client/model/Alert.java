package com.codelogic.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alert {
	private Long alertId;
	private Long locationId;
	private String email;
	private String vaccineName;
	private String vaccineType;
}
