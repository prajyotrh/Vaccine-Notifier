package com.codelogic.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.codelogic.client.model.Location;

import ch.qos.logback.classic.Logger;


@RestController
@RequestMapping("/vaccine-notifier-admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
	
	@Autowired
	private RestTemplate template;
	
	String url = "http://VACCINE-NOTIFIER-SERVICE/admin";

	
	// get all locations
	@GetMapping("/all-locations")
	public Location[] getAllLocations() {
		
		ResponseEntity<Location[]> response = template.getForEntity(url + "/all-locations", Location[].class);
		Location[] locations = response.getBody();
		return locations;
	}
	
	// add location
	@PostMapping("/add-location")
	public Location addLocation(@RequestBody Location location) {
		return template.postForObject(url+"/add-location", location, Location.class);
	}
	
	// update location
	@PutMapping("/update-location/{locationId}")
	public void updateLocation(@PathVariable("locationId") Long locationId, @RequestBody Location location) {
		System.out.println(url+"/update-location/"+locationId);
		//return template.postForObject(url+"/update-location/"+locationId, location, Location.class);
		template.put(url+"/update-location/"+locationId, location);
	}
	
	// delete location
	@DeleteMapping("/delete-location/{locationId}")	
	public void deleteLocation(@PathVariable("locationId") Long locationId) {
		System.out.println(url+"/delete-location"+locationId);
		//template.delete(url+"/delete-location",location,Void.class);
		template.delete(url+"/delete-location/"+locationId);
	}

}
