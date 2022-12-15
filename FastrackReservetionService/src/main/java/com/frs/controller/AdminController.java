package com.frs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.frs.exception.AdminException;
import com.frs.exception.LogException;
import com.frs.model.Admin;
import com.frs.model.AdminLoginDTO;
import com.frs.model.CurrentAdminSession;
import com.frs.service.AdminLoginService;
import com.frs.service.AdminService;

@RestController
@RequestMapping("frs")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AdminLoginService adminLogin;
	
	
	@PostMapping("/createadmin")
	public ResponseEntity<Admin> createAdminHandler(@Valid @RequestBody Admin admin) throws AdminException {
		return new ResponseEntity<Admin>(adminService.createAdmin(admin),HttpStatus.CREATED);
	}
	
	@PostMapping("/admin/login")
	public ResponseEntity<CurrentAdminSession> adminLogInHandler(@Valid @RequestBody AdminLoginDTO dto) throws LogException {
		
		return new ResponseEntity<CurrentAdminSession>(adminLogin.logInAdmin(dto),HttpStatus.ACCEPTED );
		
	}
	
	@PutMapping("/updateadmin")
	public  ResponseEntity<Admin> updateAdminHandler(@Valid @RequestBody Admin admin,@RequestParam(required = false) String key ) throws AdminException {
		
		return new ResponseEntity<Admin>(adminService.updateAdmin(admin, key),HttpStatus.OK);
		
	}
	
	@PostMapping("/admin/logout")
	public ResponseEntity<String> adminLogOutHandler(@RequestParam(required = false) String key) throws LogException {
		
		return new ResponseEntity<String>(adminLogin.logOutAdmin(key),HttpStatus.ACCEPTED );
		
	}
	
	

}
