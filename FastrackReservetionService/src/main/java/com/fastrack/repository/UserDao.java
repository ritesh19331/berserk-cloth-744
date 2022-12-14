package com.fastrack.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.fastrack.model.User;

public interface UserDao extends JpaRepository<User, Integer> {

	public User findByMobileNumber(String mobileNumber);
	public User findByEmail(String email);
	
}
