package com.hypothesis.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hypothesis.cms.dto.UserDto;
import com.hypothesis.cms.exception.CustomException;
import com.hypothesis.cms.model.User;
import com.hypothesis.cms.repository.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User registerUser(UserDto userDto) {
		User user = new User();
		user.setUsername(userDto.getUserName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		return userRepository.save(user);
	}

	@Override
	public User updateUserProfile(UserDto userDto, Long userId) {
		if (!userRepository.existsById(userId)) {
			throw new CustomException("there is no such user with Id: "+userId);
		}
		Optional<User> user = userRepository.findById(userId);
		user.get().setUsername(userDto.getUserName());
		user.get().setEmail(userDto.getEmail());
		return userRepository.save(user.get());
	}

	@Override
	public User deleteUserById(Long userId) {
		if (! userRepository.existsById(userId)) {
			throw new CustomException("there is no such user with Id: "+userId);
		}
		Optional<User> user = userRepository.findById(userId);
		userRepository.deleteById(userId);
		return user.get();
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long userId) {
		if (! userRepository.existsById(userId)) {
			throw new CustomException("there is no such user with Id: "+userId);
		}
		return userRepository.findById(userId).get();
	}

	@Override
	public User changeUserPassword(String password, Long userId) {
		if (!userRepository.existsById(userId)) {
			throw new CustomException("there is no such user with Id: "+userId);
		}
		Optional<User> user = userRepository.findById(userId);
		user.get().setPassword(password);
		return userRepository.save(user.get());
	}

	@Override
	public Boolean loginUser(String userName, String password) {
		List<User> users = userRepository.findAll();
		boolean validate = false;
		users.stream().forEachOrdered(e -> {
			if (e.getUsername().equals(userName) && e.getPassword().equals(password)) {
				return;
			}
		});
		return validate;
	}

}
