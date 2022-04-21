package com.codelogic.service;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codelogic.modal.Alert;
import com.codelogic.modal.Location;
import com.codelogic.repository.AlertInterface;
import com.codelogic.repository.LocationInterface;

@Service
public class LocationService {

	@Autowired
	private LocationInterface locationInterface;
	
	@Autowired
	private AlertInterface alertInterface;
	
	@Autowired
	private EmailService emailService;
	
	public List<Location> getAllLocations() {
		return locationInterface.findAll();
	}

	public Location addLocation(Location location) {
		return locationInterface.save(location);
	}

	public Location updateLocation(Location location) {
		Location locationObj = locationInterface.findById(location.getLocationId()).orElse(null);
		
		if(locationObj == null) {
			return new Location();
		}
		
		updateUsers(location);
		
		return locationInterface.save(location);
		
	}

	public void deleteLocation(Long locationId) {
		locationInterface.deleteById(locationId);
	}
	
	private void updateUsers(Location location) {
		
		List<Alert> alerts = alertInterface.findAllByLocationId(location.getLocationId());
		
		for (Alert alert : alerts) {
			if(alert.getVaccineName().equalsIgnoreCase(location.getVaccineName()) &&
			   alert.getVaccineType().equalsIgnoreCase(location.getVaccineType())) {
				
				try {
					emailService.sendVaccineNotification(alert.getEmail(),location);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
