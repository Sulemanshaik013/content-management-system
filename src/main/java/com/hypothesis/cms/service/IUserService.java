package com.hypothesis.cms.service;

import java.util.List;

import com.hypothesis.cms.dto.UserDto;

public interface IUserService {

	UserDto registerUser(UserDto userDto);

	UserDto updateUserProfile(UserDto userDto, Long userId);

	UserDto deleteUserByID(Long userId);

	List<UserDto> getAllUsers();

	UserDto getUserByID(Long userId);

	UserDto changeUserPassword(String password, Long userId);

	Boolean loginUser(String userName, String password);

}
