package com.fastrack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastrack.exception.AdminException;
import com.fastrack.exception.RouteException;
import com.fastrack.model.CurrentAdminSession;
import com.fastrack.model.Route;
import com.fastrack.repository.AdminSessionDao;
import com.fastrack.repository.RouteDao;

@Service
public class RouteServiceImpl implements RouteService{
	
	@Autowired
	private RouteDao rDao;
	
	@Autowired
	private AdminSessionDao adminSessionDao;

	@Override
	public Route addRoute(Route route, String key) throws RouteException, AdminException {
		CurrentAdminSession currentAdminSession= adminSessionDao.findByUpdateKey(key);
		if(currentAdminSession==null) throw new AdminException("Admin Not Logged In");
		return rDao.save(route);
	}

	@Override
	public Route updateRoute(Route route, String key) throws RouteException, AdminException {
		if(adminSessionDao.findByUpdateKey(key)==null) throw new AdminException("Admin Not Logged Id");
		return rDao.save(route);
	}

	@Override
	public Route deleteRoute(Integer routeId, String key) throws RouteException, AdminException {
		if(adminSessionDao.findByUpdateKey(key)==null) throw new AdminException("Admin Not Logged Id");
		Optional<Route> opt=rDao.findById(routeId);
		if(opt.isEmpty()) throw new RouteException("No Route Found with routeId :"+ routeId);
		Route route=opt.get();
		rDao.deleteById(routeId);
		return route;
	}

	@Override
	public Route viewRoute(Integer routeId, String key) throws RouteException, AdminException {
		if(adminSessionDao.findByUpdateKey(key)==null) throw new AdminException("Admin Not Logged Id");
		Optional<Route> opt=rDao.findById(routeId);
		if(opt.isEmpty()) throw new RouteException("No Route Found with routeId :"+ routeId);
		return opt.get();
	}

	@Override
	public List<Route> viewAllRoute(String key) throws RouteException, AdminException {
		if(adminSessionDao.findByUpdateKey(key)==null) throw new AdminException("Admin Not Logged Id");
		List<Route> routeList=rDao.findAll();
		return routeList;
	}

}
