package com.SAIB.IdeationPlatform.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.SAIB.IdeationPlatform.config.ApiSuccessPayload;
import com.SAIB.IdeationPlatform.model.User;
import com.SAIB.IdeationPlatform.service.UserService;
import com.SAIB.IdeationPlatform.util.Results;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/users")
	public ResponseEntity<ApiSuccessPayload> getAllUsers() {
		List<User> list = userService.getAllUser();

		ApiSuccessPayload payload = ApiSuccessPayload.build(list, "Users Fetched", HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response = new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.OK);

		return response;

	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<ApiSuccessPayload> getUserById(@PathVariable long userId) {
		User user = userService.getUserById(userId);
		ApiSuccessPayload payload = ApiSuccessPayload.build(user, "Success", HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response = new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.OK);
		return response;
	}

	@PostMapping("/user")
	public ResponseEntity<ApiSuccessPayload> addUser(@Valid @RequestBody User user,
			@RequestHeader(name = "Authorization") String token) {
		ResponseEntity<ApiSuccessPayload> response = null;
		System.out.println(user);
		String result = userService.addUser(user, token);

		if (result.equalsIgnoreCase(Results.SUCCESS)) {
			ApiSuccessPayload payload = ApiSuccessPayload.build(result, "User created successfully",
					HttpStatus.CREATED);
			response = new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.CREATED);
		}

		return response;

	}

	@PostMapping("/signup")
	public ResponseEntity<ApiSuccessPayload> createUser(@Valid @RequestBody User user) {
		ResponseEntity<ApiSuccessPayload> response = null;
		System.out.println(user);
		String result = userService.createUser(user);
		if (result.equalsIgnoreCase(Results.SUCCESS)) {
			ApiSuccessPayload payload = ApiSuccessPayload.build(result, "User created successfully",
					HttpStatus.CREATED);
			response = new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.CREATED);
		}

		return response;

	}

	@DeleteMapping("/user/{userId}")
	public ResponseEntity<ApiSuccessPayload> deleteUser(@PathVariable long userId,
			@RequestHeader(name = "Authorization") String token) {
		String result = userService.deleteUser(userId, token);
		ApiSuccessPayload payload = ApiSuccessPayload.build(result, result, HttpStatus.OK);
		ResponseEntity<ApiSuccessPayload> response = new ResponseEntity<ApiSuccessPayload>(payload, HttpStatus.OK);
		return response;

	}

}
