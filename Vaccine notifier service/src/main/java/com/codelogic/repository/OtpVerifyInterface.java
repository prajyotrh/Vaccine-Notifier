package com.codelogic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codelogic.modal.OtpVerification;

public interface OtpVerifyInterface extends JpaRepository<OtpVerification, Long>{

	public OtpVerification getByEmail(String email);
}
