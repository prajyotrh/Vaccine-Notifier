package com.codelogic.client.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
	
	private Long locationId;
	private String locationName;
	private long vaccineCount;
	private Date date;
	private String vaccineName;
	private String vaccineType;

}
