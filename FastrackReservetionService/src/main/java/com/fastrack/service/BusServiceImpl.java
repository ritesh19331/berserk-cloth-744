package com.fastrack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastrack.exception.AdminException;
import com.fastrack.exception.BusException;
import com.fastrack.exception.RouteException;
import com.fastrack.model.Bus;
import com.fastrack.model.CurrentAdminSession;
import com.fastrack.model.Route;
import com.fastrack.repository.AdminDao;
import com.fastrack.repository.AdminSessionDao;
import com.fastrack.repository.BusDao;
import com.fastrack.repository.RouteDao;


@Service
public class BusServiceImpl  implements BusService{
	
	@Autowired
	private BusDao busDao;
	
	@Autowired
	private RouteDao routeDao;
	
	@Autowired
	private AdminSessionDao adminSessionDao;
	
	@Override
	public Bus addBus(Bus bus, Integer routeId,String key) throws BusException, AdminException , RouteException{
		
		CurrentAdminSession currentAdminSession =	adminSessionDao.findByUpdateKey(key);
		
		if(currentAdminSession==null)
			throw new AdminException("Admin Login Required...");
		Optional<Route> opt=routeDao.findById(routeId);
		if(opt.isEmpty()) throw new RouteException("Route no present with id :"+ routeId);
		
		Route route=opt.get();
		bus.setBusRoute(route);
		Bus b=null;
		try {
			b = busDao.save(bus);
		} catch (IllegalArgumentException e) {
			throw new  BusException("Please Fill Proper Details");
		}
		

		return b;
	}

	@Override
	public Bus updateBus(Bus bus,String key) throws BusException ,AdminException {
		 CurrentAdminSession currentAdminSession =	adminSessionDao.findByUpdateKey(key);
		
		if(currentAdminSession==null)
			throw new AdminException("Admin Login Required...");
		
		Optional<Bus> opt =busDao.findById(bus.getBusId());
		
		if(opt.isEmpty())
			throw new BusException("Bus Not Found With Id: "+bus.getBusId());
		Bus b =opt.get();
		
		
		return busDao.save(bus);
	}
	
	@Override
	public Bus deleteBus(Integer busId,String key) throws BusException  ,AdminException {
		CurrentAdminSession currentAdminSession =	adminSessionDao.findByUpdateKey(key);
		
		if(currentAdminSession==null)
			throw new AdminException("Admin Login Required...");
		Optional<Bus> opt = busDao.findById(busId);
		if(opt.isEmpty())
			throw new BusException("Bus Not Found With id:"+busId);
		
		busDao.delete(opt.get());
		
		return opt.get();
	}
	
	@Override
	public Bus viewBus(Integer busId,String key) throws BusException  ,AdminException {
		CurrentAdminSession currentAdminSession =	adminSessionDao.findByUpdateKey(key);
		
		if(currentAdminSession==null)
			throw new AdminException("Admin Login Required...");
		Optional<Bus> opt = busDao.findById(busId);
		if(opt.isEmpty())
			throw new BusException("Bus Not Found With id:"+busId);
		
		return opt.get();
	}

	@Override
	public List<Bus> viewAllBus(String key) throws BusException ,AdminException  {
		
		CurrentAdminSession currentAdminSession =	adminSessionDao.findByUpdateKey(key);
		
		if(currentAdminSession==null)
			throw new AdminException("Admin Login Required...");
		List<Bus> buses = busDao.findAll();
		if(buses.size()==0)
			throw new BusException("No Bus Found");
		
		return buses;
	}

	@Override
	public List<Bus> viewAllBusByType(String type,String key) throws BusException ,AdminException  {
		
		CurrentAdminSession currentAdminSession =	adminSessionDao.findByUpdateKey(key);
		
		if(currentAdminSession==null)
			throw new AdminException("Admin Login Required...");
		
		List<Bus> busList =busDao.findByBusType(type);
		
		if(busList.size()==0)
			throw new BusException("No Bus Found For Type: "+type);
		
		return busList;
	}

}
