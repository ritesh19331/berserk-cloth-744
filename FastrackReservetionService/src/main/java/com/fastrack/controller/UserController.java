package com.fastrack.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fastrack.exception.AdminException;
import com.fastrack.exception.LogException;
import com.fastrack.exception.UserException;
import com.fastrack.model.CurrentUserSession;
import com.fastrack.model.User;
import com.fastrack.model.UserLoginDTO;
import com.fastrack.service.UserLoginService;
import com.fastrack.service.UserService;

@RestController
@RequestMapping("frs")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserLoginService userLogin;

	@PostMapping("/createuser")
	public ResponseEntity<User> createUserHandler(@Valid @RequestBody User user) throws UserException{
		return new ResponseEntity<User>(userService.createUser(user),HttpStatus.CREATED);
	}
	@PostMapping("/user/login")
	public ResponseEntity<CurrentUserSession> userLogInHandler(@Valid @RequestBody UserLoginDTO dto)
			throws LogException {

		return new ResponseEntity<CurrentUserSession>(userLogin.logInUser(dto), HttpStatus.ACCEPTED);

	}

	@PostMapping("/user/logout")
	public String userLogOutHandler(@RequestParam(required = false) String key) throws LogException {

		return userLogin.logOutUser(key);

	}

	@PutMapping("/updateuser")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @RequestParam(required = false) String key)
			throws UserException {

		return new ResponseEntity<User>(userService.updateUser(user, key), HttpStatus.OK);

	}

	@DeleteMapping("/deleteuser")
	public ResponseEntity<User> deleteUser(@RequestParam(required = false) Integer userId,
			@RequestParam(required = false) String adminUpdateKey) throws UserException, AdminException {

		return new ResponseEntity<User>(userService.deleteUser(userId, adminUpdateKey), HttpStatus.OK);

	}

	@GetMapping("/viewuser")
	public ResponseEntity<User> viewUser(@RequestParam(required = false) Integer userId,
			@RequestParam(required = false) String adminUpdateKey) throws UserException, AdminException {

		return new ResponseEntity<User>(userService.viewUser(userId, adminUpdateKey), HttpStatus.OK);

	}

	@GetMapping("/viewallusers")
	public ResponseEntity<List<User>> viewAllUser(@RequestParam(required = false) String adminUpdatekey)
			throws UserException, AdminException {

		return new ResponseEntity<List<User>>(userService.viewAllUsers(adminUpdatekey), HttpStatus.OK);

	}

}
