/**  
 * Copyright (c) 2016-2026,Pay1pay Technology Co., Ltd. All rights reserved. 
 *
 */

package com.midea.junit.test.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.midea.junit.test.domain.Constant.Key;
import com.midea.junit.test.domain.Result;
import com.midea.junit.test.domain.User;
import com.midea.junit.test.repository.UserRepository;

/**
 * 
 * controller单元测试
 * 
 * @author <a href="mailto:yushun.qin@midea.com">Yushun.Qin</a>
 * @createTime 2017年8月25日 下午8:29:50
 * @since JDK1.7
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserControllerTest {
	private MockMvc mockMvc;
	@Autowired
	protected WebApplicationContext wac;
	@Autowired
	UserRepository userRepository;

	/**
	 * 测试姓名
	 */
	private final String TEST_NAME = "qinys" + getFixedLengthRandomNo(3);
	/**
	 * 测试年龄
	 */
	private final String TEST_AGE = "19";

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

	/**
	 * 该方法在每个方法执行之前都会执行一遍
	 * 
	 * @author Yushun.Qin on 2017年8月26日 上午9:38:16
	 */
	@Before
	public void setup() {
		mockMvc = webAppContextSetup(this.wac).build();
	}

	@Test
	public void testSave() throws Exception {
		// 1.准备数据
		MultiValueMap<String, String> params = new HttpHeaders();
		params.add(Key.NAME, TEST_NAME);
		params.add(Key.AGE, TEST_AGE);

		// 2.执行方法
		MvcResult mvcResult = mockMvc.perform(post("/saveUser").params(params)).andDo(print()).andReturn();

		// 3.校验结果
		Result result = JSON.toJavaObject(JSON.parseObject(mvcResult.getResponse().getContentAsString()), Result.class);
		User user = JSON.toJavaObject((JSON) result.getData(), User.class);
		assertEquals(user.getName(), TEST_NAME);
		assertEquals(user.getAge(), Integer.valueOf(TEST_AGE));
		
		// 4.删除数据
		userRepository.delete(user.getId());
	}
	
	@Test
	public void testFindUsersByName() throws Exception {
		// 1.准备数据
		User user = new User(TEST_NAME, Integer.parseInt(TEST_AGE));
		userRepository.save(user);
		
		MultiValueMap<String, String> params = new HttpHeaders();
		params.add(Key.NAME, TEST_NAME);
		
		// 2.执行方法
		MvcResult mvcResult = mockMvc.perform(post("/findUsersByName").params(params)).andDo(print()).andReturn();
		
		// 3.校验结果
		Result result = JSON.toJavaObject(JSON.parseObject(mvcResult.getResponse().getContentAsString()), Result.class);
		JSONArray userList = (JSONArray)result.getData();
		List<User> existUserList = new ArrayList<>();
		existUserList.add(user);
		JSONArray existUserJsonArray = JSON.parseArray(JSON.toJSONString(existUserList));
		assertEquals(existUserJsonArray, userList);
		
		// 4.删除数据
		userRepository.delete(user.getId());
	}
	
	@Test
	public void testFindUsersById() throws Exception {
		// 1.准备数据
		User user = new User(TEST_NAME, Integer.parseInt(TEST_AGE));
		userRepository.save(user);
		
		MultiValueMap<String, String> params = new HttpHeaders();
		params.add(Key.ID, user.getId());
		
		// 2.执行方法
		MvcResult mvcResult = mockMvc.perform(post("/findUserById").params(params)).andDo(print()).andReturn();
		
		// 3.校验结果
		Result result = JSON.toJavaObject(JSON.parseObject(mvcResult.getResponse().getContentAsString()), Result.class);
		User actualUser = JSON.toJavaObject((JSON) result.getData(), User.class);
		assertEquals(user, actualUser);
		
		// 4.删除数据
		userRepository.delete(user.getId());
	}

	@Test
	public void testSaveException() throws Exception {
		// 1.准备数据
		MultiValueMap<String, String> params = new HttpHeaders();
		params.add(Key.NAME, null);
		params.add(Key.AGE, TEST_AGE);

		// 2.执行方法
		MvcResult mvcResult = mockMvc.perform(post("/saveUser").params(params)).andDo(print()).andReturn();
		
		//3.校验结果
		assertTrue(mvcResult.getResponse().getContentAsString().contains("参数错误"));
	}
}
