package com.midea.junit.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midea.junit.test.domain.User;
import com.midea.junit.test.repository.UserRepository;
import com.midea.junit.test.util.AssertUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public void save(User user) {
		AssertUtil.notEmpty(user);
		AssertUtil.notEmpty(user.getName());
		
		userRepository.save(user);
	}

	@Override
	public List<User> findUserByName(String name) {
		AssertUtil.notEmpty(name);
		
		return userRepository.findUserByName(name);
	}

	@Override
	public User findUserById(String id) {
		AssertUtil.notEmpty(id);
		
		return userRepository.findUserById(id);
	}

}
