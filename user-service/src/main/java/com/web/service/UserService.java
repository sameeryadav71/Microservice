package com.web.service;

import java.util.List;

import com.web.entity.User;
import com.web.exception.RecordNotFoundException;

public interface UserService {
	
	public User saveUser(User user) throws RecordNotFoundException;
	public User updateUser(Integer id, User user) throws RecordNotFoundException ;
	public void deleteUser(Integer id) throws RecordNotFoundException;
	public User findUserById(Integer id) throws RecordNotFoundException ;
	public User findByUserName(String userName) throws RecordNotFoundException ;
	public List<User> findAllUsers();

}
