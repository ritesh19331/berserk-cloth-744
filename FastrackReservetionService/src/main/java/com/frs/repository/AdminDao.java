package com.frs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frs.model.Admin;



public interface AdminDao extends JpaRepository<Admin, Integer> {

	
	public Admin findByEmail(String email);
	
}
