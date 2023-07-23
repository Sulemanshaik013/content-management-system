package com.hypothesis.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hypothesis.cms.dto.UserDto;
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
	public UserDto registerUser(@RequestBody UserDto userDto) {
		return userService.registerUser(userDto);
	}

	@GetMapping("/login")
	public Boolean loginUser(@RequestParam String userName, @RequestParam String password) {
		return userService.loginUser(userName, password);
	}

	@PutMapping("/update")
	public UserDto updateUserProfile(@RequestBody UserDto userDto, @RequestParam Long userId) {
		return userService.updateUserProfile(userDto, userId);
	}

	@PatchMapping("/update")
	public UserDto changeUserPassword(@RequestParam String password, @RequestParam Long userId) {
		return userService.changeUserPassword(password, userId);
	}

	@GetMapping("/read")
	public UserDto getUserByID(@RequestParam Long userId) {
		return userService.getUserByID(userId);
	}

	@GetMapping("/read")
	public List<UserDto> getAllUsers() {
		return userService.getAllUsers();
	}

	@DeleteMapping("/delete")
	public UserDto deleteUser(@RequestParam Long userId) {
		return userService.deleteUserByID(userId);
	}

}
