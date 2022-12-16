package com.fastrack.service;

import java.util.List;

import com.fastrack.exception.AdminException;
import com.fastrack.exception.RouteException;
import com.fastrack.model.Route;

public interface RouteService {
	public Route addRoute(Route route, String key) throws RouteException, AdminException;
	public Route updateRoute(Route route, String key) throws RouteException, AdminException;
	public Route deleteRoute(Integer routeId, String key) throws RouteException, AdminException;
	public Route viewRoute(Integer routeId, String key) throws RouteException, AdminException;
	public List<Route> viewAllRoute(String key) throws RouteException, AdminException;
}
