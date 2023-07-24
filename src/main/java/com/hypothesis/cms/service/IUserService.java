package com.hypothesis.cms.service;

import java.util.List;

import com.hypothesis.cms.dto.UserDto;
import com.hypothesis.cms.model.User;

public interface IUserService {

	User registerUser(UserDto userDto);

	User updateUserProfile(UserDto userDto, Long userId);

	User deleteUserById(Long userId);

	List<User> getAllUsers();

	User getUserById(Long userId);

	User changeUserPassword(String password, Long userId);

	Boolean loginUser(String userName, String password);

}
