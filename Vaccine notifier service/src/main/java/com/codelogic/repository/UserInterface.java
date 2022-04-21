package com.codelogic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codelogic.modal.User;

public interface UserInterface extends JpaRepository<User, Long>{
	
	public User findByEmail(String email);

}
