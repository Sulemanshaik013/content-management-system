package com.hypothesis.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hypothesis.cms.dto.UserDto;
import com.hypothesis.cms.exception.CustomException;
import com.hypothesis.cms.model.User;
import com.hypothesis.cms.repository.UserRepository;
import com.hypothesis.cms.util.SecurityUtil;

@Service
public class UserService implements IUserService {

	private static final String NO_SUCH_USER_WITH_ID = "there is no such user with Id: ";

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SecurityUtil securityUtil;

	@Override
	public User registerUser(UserDto userDto) {
		User user = new User();
		user.setName(userDto.getName());
		user.setUsername(userDto.getUserName());
		user.setPassword(securityUtil.encrypt(userDto.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User updateUserProfile(UserDto userDto, Long userId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new CustomException(NO_SUCH_USER_WITH_ID + userId));
		if (userDto.getName() != null)
			user.setName(userDto.getName());
		if (userDto.getUserName() != null)
			user.setUsername(userDto.getUserName());
		return userRepository.save(user);
	}

	@Override
	public User deleteUserById(Long userId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new CustomException(NO_SUCH_USER_WITH_ID + userId));
		userRepository.deleteById(userId);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new CustomException(NO_SUCH_USER_WITH_ID + userId));
	}

	@Override
	public User changeUserPassword(String password, Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new CustomException(NO_SUCH_USER_WITH_ID + userId));
		user.setPassword(securityUtil.encrypt(password));
		return userRepository.save(user);
	}

	@Override
	public Boolean loginUser(String userName, String password) {
		List<User> users = userRepository.findAll();
		boolean validate = false;
		for (User user : users) {
			if (user.getUsername().equals(userName) && securityUtil.decrypt(user.getPassword()).equals(password))
				validate = true;
		}
		return validate;
	}

}
