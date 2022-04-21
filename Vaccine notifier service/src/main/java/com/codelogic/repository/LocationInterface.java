package com.codelogic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codelogic.modal.Location;

public interface LocationInterface extends JpaRepository<Location, Long>{

}
