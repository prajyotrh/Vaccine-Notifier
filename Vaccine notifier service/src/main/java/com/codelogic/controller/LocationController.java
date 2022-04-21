package com.codelogic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codelogic.modal.Location;
import com.codelogic.service.LocationService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class LocationController {
	
	@Autowired
	private LocationService locationService;
	
	// get all locations
	@GetMapping("/all-locations")
	public List<Location> getAllLocations() {
		return locationService.getAllLocations();
	}
	
	// add location
	@PostMapping("/add-location")
	public Location addLocation(@RequestBody Location location) {
		return locationService.addLocation(location);
	}
	
	// update location	
	@PutMapping("/update-location/{locationId}")
	public Location updateLocation(@PathVariable("locationId") Long locationId, @RequestBody Location location) {
		return locationService.updateLocation(location);
	}
	
	// delete location
	@DeleteMapping("/delete-location/{locationId}")
	public void deleteLocation(@PathVariable("locationId") Long locationId) {
		locationService.deleteLocation(locationId);
	}
}
