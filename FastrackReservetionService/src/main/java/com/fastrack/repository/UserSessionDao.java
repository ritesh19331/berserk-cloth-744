package com.fastrack.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.fastrack.model.CurrentUserSession;


public interface UserSessionDao extends JpaRepository<CurrentUserSession, Integer> {
	
	
	public CurrentUserSession findByUpdateKey(String updateKey);
	
}
