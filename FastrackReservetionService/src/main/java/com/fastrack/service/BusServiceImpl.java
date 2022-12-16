package com.fastrack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastrack.exception.AdminException;
import com.fastrack.exception.BusException;
import com.fastrack.model.Bus;
import com.fastrack.model.CurrentAdminSession;
import com.fastrack.repository.AdminDao;
import com.fastrack.repository.AdminSessionDao;
import com.fastrack.repository.BusDao;


@Service
public class BusServiceImpl  implements BusService{
	
	@Autowired
	private BusDao busDao;
	
	@Autowired
	private AdminSessionDao adminSessionDao;
	
	@Override
	public Bus addBus(Bus bus,String key) throws BusException, AdminException {
		
		CurrentAdminSession currentAdminSession =	adminSessionDao.findByUpdateKey(key);
		
		if(currentAdminSession==null)
			throw new AdminException("Admin Login Required...");
		
		
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
