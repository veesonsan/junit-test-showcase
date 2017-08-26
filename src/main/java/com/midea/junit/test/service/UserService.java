package com.midea.junit.test.service;

import java.util.List;

import com.midea.junit.test.domain.User;


public interface UserService {

	void save(User user);

	List<User> findUserByName(String name);

	User findUserById(String id);

}
