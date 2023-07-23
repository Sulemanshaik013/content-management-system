package com.hypothesis.cms.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hypothesis.cms.dto.UserDto;
import com.hypothesis.cms.model.User;
import com.hypothesis.cms.repository.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public UserDto registerUser(UserDto userDto) {
		User user = new User();
		user.setUsername(userDto.getUserName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		return mapper.map(userRepository.save(user), UserDto.class);
	}

	@Override
	public UserDto updateUserProfile(UserDto userDto, Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			user.get().setUsername(userDto.getUserName());
			user.get().setEmail(userDto.getEmail());
		}
		return mapper.map(userRepository.save(user.get()), UserDto.class);
	}

	@Override
	public UserDto deleteUserByID(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			userRepository.deleteById(userId);
			return mapper.map(user.get(), UserDto.class);
		}
		return mapper.map(userRepository.save(user.get()), UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		return mapper.map(userRepository.findAll(), new TypeToken<List<UserDto>>() {
		}.getType());
	}

	@Override
	public UserDto getUserByID(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			return mapper.map(userRepository.findById(userId), UserDto.class);
		}
		return null;
	}

	@Override
	public UserDto changeUserPassword(String password, Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			user.get().setPassword(password);
		}
		return mapper.map(userRepository.save(user.get()), UserDto.class);
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
