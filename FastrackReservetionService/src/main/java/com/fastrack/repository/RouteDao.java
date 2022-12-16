package com.fastrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fastrack.model.Route;

@Repository
public interface RouteDao extends JpaRepository<Route, Integer> {
	
}
