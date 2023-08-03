package com.hypothesis.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hypothesis.cms.dto.UserDto;
import com.hypothesis.cms.model.User;
import com.hypothesis.cms.service.IUserService;

@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {

	@Autowired
	private IUserService userService;

	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) {
		User registeredUser = userService.registerUser(userDto);
		return new ResponseEntity<>(registeredUser, HttpStatus.OK);
	}

	@GetMapping("/login")
	public ResponseEntity<Boolean> loginUser(@RequestParam String userName, @RequestParam String password) {
		Boolean isValidUser = userService.loginUser(userName, password);
		return new ResponseEntity<>(isValidUser, HttpStatus.OK);
	}

	@PutMapping("/update/{userId}")
	public ResponseEntity<User> updateUserProfile(@RequestBody UserDto userDto, @PathVariable String userId) {
		User updatedUser = userService.updateUserProfile(userDto, Long.parseLong(userId));
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	@PatchMapping("/update/{userId}")
	public ResponseEntity<User> changeUserPassword(@RequestParam String password, @PathVariable Long userId) {
		User updatedUser = userService.changeUserPassword(password, userId);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	@GetMapping("/read/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Long userId) {
		User user = userService.getUserById(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/read")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> allUsers = userService.getAllUsers();
		return new ResponseEntity<>(allUsers, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<User> deleteUser(@PathVariable Long userId) {
		User deletedUser = userService.deleteUserById(userId);
		return new ResponseEntity<>(deletedUser, HttpStatus.OK);
	}

}
