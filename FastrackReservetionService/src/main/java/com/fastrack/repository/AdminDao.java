package com.fastrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastrack.model.Admin;



public interface AdminDao extends JpaRepository<Admin, Integer> {

	
	public Admin findByEmail(String email);
	
}
