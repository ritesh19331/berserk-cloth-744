package com.fastrack.service;

import java.util.List;

import com.fastrack.exception.AdminException;
import com.fastrack.exception.BusException;
import com.fastrack.model.Bus;

public interface BusService {
	
	public Bus addBus(Bus bus,String key) throws BusException , AdminException;
	public Bus updateBus(Bus bus,String key) throws BusException ,AdminException;
	public Bus deleteBus(Integer budId,String key) throws BusException ,AdminException;
	public Bus viewBus(Integer busId,String key) throws BusException ,AdminException;
	public List<Bus> viewAllBus(String key) throws BusException ,AdminException;
	public List<Bus> viewAllBusByType(String type,String key) throws BusException ,AdminException;
	
	
	
}
