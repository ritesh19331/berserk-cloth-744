package com.frs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frs.model.CurrentAdminSession;

public interface AdminSessionDao extends JpaRepository<CurrentAdminSession, Integer> {

	public  CurrentAdminSession findByUpdateKey(String updateKey);

}
