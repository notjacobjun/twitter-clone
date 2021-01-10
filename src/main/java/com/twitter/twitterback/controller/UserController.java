package com.twitter.twitterback.controller;

import java.util.List;

import com.twitter.twitterback.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

	@Autowired
	private com.twitter.twitterback.service.UserService userService;

	@RequestMapping("/users")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces="application/json", value = "/register")
	public void registerUser(@RequestBody User user){
		userService.registerUser(user);
	}

	@GetMapping(value = "/getUserById/{id}")
	public User getUser(@PathVariable long id){
		return userService.getUser(id);
	}

	//todo handle exceptions, not found, validations
	@GetMapping(value = "/user/{userName}")
	public ResponseEntity<User> getUser(@PathVariable String userName){
		User user = userService.getUserByUsername(userName);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	//todo login
	//todo getCountries
	//todo getGenders
	//todo getByNickname
	//todo settings table for user. privacy settings for example
}
