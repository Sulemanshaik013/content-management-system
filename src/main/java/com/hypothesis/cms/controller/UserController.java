package com.hypothesis.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public User registerUser(@RequestBody UserDto userDto) {
		return userService.registerUser(userDto);
	}

	@GetMapping("/login")
	public Boolean loginUser(@RequestParam String userName, @RequestParam String password) {
		return userService.loginUser(userName, password);
	}

	@PutMapping("/update/{userId}")
	public User updateUserProfile(@RequestBody UserDto userDto, @PathVariable String userId) {
		return userService.updateUserProfile(userDto, Long.parseLong(userId));
	}

	@PatchMapping("/update/{userId}")
	public User changeUserPassword(@RequestParam String password, @PathVariable Long userId) {
		return userService.changeUserPassword(password, userId);
	}

	@GetMapping("/read/{userId}")
	public User getUserById(@PathVariable Long userId) {
		return userService.getUserById(userId);
	}

	@GetMapping("/read")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@DeleteMapping("/delete/{userId}")
	public User deleteUser(@PathVariable Long userId) {
		return userService.deleteUserById(userId);
	}

}
