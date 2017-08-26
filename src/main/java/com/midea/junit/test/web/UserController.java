package com.midea.junit.test.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midea.junit.test.domain.Result;
import com.midea.junit.test.domain.User;
import com.midea.junit.test.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("saveUser")
	public Result saveUser(String name, int age) {
		User user = new User(name, age);
		userService.save(user);
		return commonSuccess(user);
	}

	@RequestMapping("findUsersByName")
	public Result findUsersByName(String name) {
		List<User> userList = userService.findUserByName(name);
		return commonSuccess(userList);
	}

	@RequestMapping("findUserById")
	public Result findUserById(String id) {
		User user = userService.findUserById(id);
		return commonSuccess(user);
	}

	private Result commonSuccess() {
		Result result = new Result();
		result.setCode(10001);
		return result;
	}

	private Result commonSuccess(Object data) {
		Result result = commonSuccess();
		result.setData(data);
		return result;
	}
}
