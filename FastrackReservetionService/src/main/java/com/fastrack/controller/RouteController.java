package com.fastrack.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fastrack.exception.AdminException;
import com.fastrack.exception.RouteException;
import com.fastrack.model.Route;
import com.fastrack.service.RouteService;

@RestController
public class RouteController {
	@Autowired
	private RouteService routeService;
	
	@PostMapping("/admin/route/{key}")
	public ResponseEntity<Route> addRouteHandler(@Valid @RequestBody Route route,@PathVariable("key") String key) throws RouteException, AdminException{
		Route addedRoute=routeService.addRoute(route, key);
		return new ResponseEntity<>(addedRoute,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/admin/route/{key}")
	public ResponseEntity<Route> updateRouteHandler(@Valid @RequestBody Route route, @PathVariable("key") String key) throws RouteException, AdminException{
		Route updatedRoute=routeService.updateRoute(route, key);
		return new ResponseEntity<Route>(updatedRoute,HttpStatus.OK);
	}
	
	@DeleteMapping("/admin/route/{id}/{key}")
	public ResponseEntity<Route> deleteRouteByIdHandler(@PathVariable("id") Integer id, @PathVariable("key") String key) throws RouteException, AdminException{
		Route deletedRoute=routeService.deleteRoute(id, key);
		return new ResponseEntity<Route>(deletedRoute,HttpStatus.OK);
	}
	
	@GetMapping("/admin/route/{id}/{key}")
	public ResponseEntity<Route> viewRouteByIdHandler(@PathVariable("id") Integer r_id,@PathVariable("key") String key) throws RouteException, AdminException{
		Route route=routeService.viewRoute(r_id, key);
		return new ResponseEntity<Route>(route,HttpStatus.OK);
	}
	
	@GetMapping("/admin/routes/{key}")
	public ResponseEntity<List<Route>> viewAllRouteHandler(@PathVariable("key") String key) throws RouteException, AdminException{
		List<Route> rList=routeService.viewAllRoute(key);
		return new ResponseEntity<List<Route>>(rList,HttpStatus.OK);
	}
	
	
	
	
}
