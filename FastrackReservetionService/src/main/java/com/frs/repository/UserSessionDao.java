package com.frs.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.frs.model.CurrentUserSession;


public interface UserSessionDao extends JpaRepository<CurrentUserSession, Integer> {
	
	
	public CurrentUserSession findByUpdateKey(String updateKey);
	
}
