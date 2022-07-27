package com.infosys.user.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.user.dto.UserDTO;
import com.infosys.user.serviceimplementation.UserServiceImplementation;

@RestController
@RequestMapping("/user/")
public class UserController {
	private UserServiceImplementation userService;

	public UserController(UserServiceImplementation userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserDTO user) {
		return new ResponseEntity<>(userService.CreateUser(user), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<Object> createUser(@PathVariable(value = "userId") long userId) {
		return new ResponseEntity<>(userService.GetProfile(userId), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{userId}/appointments")
	public ResponseEntity<Object> getAppointemnets(@PathVariable(value = "userId") long userId) {
		return new ResponseEntity<>(userService.ShowAllAppointments(userId), HttpStatus.ACCEPTED);
	}

}
