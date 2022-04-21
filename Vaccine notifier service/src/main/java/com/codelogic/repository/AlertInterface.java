package com.codelogic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codelogic.modal.Alert;

public interface AlertInterface extends JpaRepository<Alert, Long>{

	List<Alert> findAllByEmail(String email);
	
	List<Alert> findAllByLocationId(Long locationId);
	

}
