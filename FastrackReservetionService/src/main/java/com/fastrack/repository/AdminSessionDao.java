package com.fastrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastrack.model.CurrentAdminSession;

public interface AdminSessionDao extends JpaRepository<CurrentAdminSession, Integer> {

	public  CurrentAdminSession findByUpdateKey(String updateKey);

}
