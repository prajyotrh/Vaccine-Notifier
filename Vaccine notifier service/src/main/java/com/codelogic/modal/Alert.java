package com.codelogic.modal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Alert {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long alertId;
	private Long locationId;
	private String email;
	private String vaccineName;
	private String vaccineType;

}
