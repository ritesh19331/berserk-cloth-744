package com.fastrack.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fastrack.exception.AdminException;
import com.fastrack.exception.BusException;
import com.fastrack.exception.RouteException;
import com.fastrack.model.Bus;
import com.fastrack.service.AdminService;
import com.fastrack.service.BusService;

@RestController
public class BusController {
	
	@Autowired
	private BusService busService;
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/admin/bus/{route_Id}/{key}")
	public ResponseEntity<Bus> addBusHandler(@Valid @RequestBody Bus bus,@PathVariable("route_Id") Integer routeId,@PathVariable("key")	String key) throws BusException, AdminException, RouteException{
		
		Bus b = busService.addBus(bus,routeId, key);
		
		return new ResponseEntity<>(b,HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/admin/bus/{key}")
	public ResponseEntity<Bus> updateBusHandler (@Valid @RequestBody Bus bus,@PathVariable("key")	String key) throws BusException, AdminException{
		
		Bus b = busService.updateBus(bus,key);
		
		return new ResponseEntity<>(b,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/admin/bus/{id}/{key}")
	public ResponseEntity<Bus> deleteBusHandler(@PathVariable("id") Integer busId,@PathVariable("key")	String key) throws BusException, AdminException{
		
		Bus b = busService.deleteBus(busId,key);
		
		return new ResponseEntity<>(b,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/admin/buses/{key}")
	public ResponseEntity<List<Bus>> viewAllBusHandler(@PathVariable("key")	String key) throws BusException, AdminException{
		
		List<Bus> b = busService.viewAllBus(key);
		
		return new ResponseEntity<>(b,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/admin/buses/{type}/{key}")
	public ResponseEntity<List<Bus>> viewAllBusByTypeHandler(@PathVariable("type") String type,	@PathVariable("key")	String key) throws BusException, AdminException{
		
		List<Bus> b = busService.viewAllBusByType(type, key);
		
		return new ResponseEntity<>(b,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/admin/bus/{id}/{key}")
	public ResponseEntity<Bus> viewBusHandler(@PathVariable("id") Integer busId	,@PathVariable("key")	String key) throws BusException, AdminException{
		
		Bus b = busService.viewBus(busId, key);
		
		return new ResponseEntity<>(b,HttpStatus.ACCEPTED);
		
	}
	
	
}
