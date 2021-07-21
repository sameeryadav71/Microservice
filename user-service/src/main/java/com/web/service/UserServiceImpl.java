package com.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.entity.User;
import com.web.exception.RecordNotFoundException;
import com.web.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User saveUser(User user) throws RecordNotFoundException {
		Optional<User> userData = userRepository.findByUserName(user.getUserName());
		if (!userData.isPresent())
			return userRepository.save(user);
		else
			throw new RecordNotFoundException("User already present!!");
	}

	@Override
	public User updateUser(Integer id, User user) throws RecordNotFoundException {
		Optional<User> entity = userRepository.findById(id);
		if (entity.isPresent()) {
			user.setId(id);
			return userRepository.save(user);
		} else
			throw new RecordNotFoundException("No user found with id :" + id);
	}

	public User createOrUpdateUser(User entity) throws RecordNotFoundException {
		Optional<User> employee = userRepository.findById(entity.getId());

		if (employee.isPresent()) {
			User newEntity = employee.get();
			newEntity.setUserName(entity.getUserName());
			newEntity.setPassword(entity.getPassword());
			newEntity.setRoles(entity.getRoles());
			newEntity.setActive(entity.isActive());
			newEntity = userRepository.save(newEntity);
			return newEntity;
		} else {
			User userData = userRepository.findByUserNameAndPassword(entity.getUserName(), entity.getPassword());
			if (userData == null) {
				entity = userRepository.save(entity);
				return entity;
			} else
				throw new RecordNotFoundException("User already present!!");
		}
	}

	@Override
	public void deleteUser(Integer id) throws RecordNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			userRepository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No user record exist for given id");
		}
	}

	@Override
	public User findUserById(Integer id) throws RecordNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new RecordNotFoundException("No user record exist for given id!!");
		}
	}

	@Override
	public User findByUserName(String userName) throws RecordNotFoundException {
		Optional<User> user = userRepository.findByUserName(userName);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new RecordNotFoundException("No user record exist for given username!!");
		}
	}

	@Override
	public List<User> findAllUsers() {
		List<User> userList = userRepository.findAll();
		if (userList.size() > 0) {
			return userList;
		} else {
			return new ArrayList<User>();
		}
	}

}
