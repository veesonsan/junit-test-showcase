/**  
 * Copyright (c) 2016-2026,Pay1pay Technology Co., Ltd. All rights reserved. 
 *
 */

package com.midea.junit.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.midea.junit.test.domain.ErrorCodeEnum;
import com.midea.junit.test.domain.User;
import com.midea.junit.test.exceptions.BusinessException;
import com.midea.junit.test.repository.UserRepository;

/**
 * 
 * Service单元测试
 * 
 * @author <a href="mailto:yushun.qin@midea.com">Yushun.Qin</a>
 * @createTime 2017年8月25日 下午8:29:50
 * @since JDK1.7
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceImplTest {
	@Autowired
	private UserService userService;
	@Autowired
	UserRepository userRepository;

	/**
	 * 测试姓名
	 */
	private final String TEST_NAME = "qinys" + getFixedLengthRandomNo(3);
	/**
	 * 测试年龄
	 */
	private final int AGE = 19;

	/**
	 * 异常规则
	 */
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	/**
	 * 生成指定长度的数字
	 * 
	 * @param len
	 * @return
	 * @author Yushun.Qin on 2017年8月17日 下午4:48:38
	 */
	private static String getFixedLengthRandomNo(int len) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < len; i++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}

	@Test
	public void testSave() {
		// 1.准备数据
		User user = new User(TEST_NAME, AGE);

		// 2.执行方法
		userService.save(user);

		// 3.校验结果
		User userDb = userService.findUserById(user.getId());
		assertEquals(userDb, user);

		// 4.清除测试数据
		userRepository.delete(user.getId());
	}

	@Test
	public void testFindUserByName() {
		// 1.准备数据
		User user = new User(TEST_NAME, AGE);
		userService.save(user);

		// 2.执行测试方法
		List<User> userDb = userService.findUserByName(TEST_NAME);

		// 3.校验测试数据
		assertTrue(userDb.size() > 0);

		// 4.清除数据
		userRepository.delete(user.getId());
	}

	@Test(expected = BusinessException.class)
	public void testSaveException() {
		// 1.准备数据
		User user = null;

		// 2.执行方法
		userService.save(user);
	}

	@Test
	public void testFindUserByNameCatchException() {
		// 1.准备数据
		String name = null;

		try {
			// 2.执行测试方法
			userService.findUserByName(name);
			
		} catch (BusinessException e) {
			// 3.校验异常错误码
			assertEquals(e.getErrCode(), ErrorCodeEnum.PARAM_ERROR.getErrorCode());
			
		}
	}

	@Test(expected = BusinessException.class)
	public void testFindUserByNameException() {
		// 1.准备数据
		String name = null;

		// 2.执行测试方法
		userService.findUserByName(name);
	}

	@Test
	public void testFindUserByNameUseExceptionRule() {
		// 1.准备数据
		String name = null;

		// 2.执行测试方法,期望参数异常
		expectedEx.expect(BusinessException.class);
		expectedEx.expectMessage("参数错误");
		userService.findUserByName(name);
	}

}
