/**  
 * Copyright (c) 2016-2026,Pay1pay Technology Co., Ltd. All rights reserved. 
 *
 */

package com.midea.junit.test.easymock;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.midea.junit.test.domain.User;
import com.midea.junit.test.service.UserService;

/**
 * 
 * Easymock 测试用法
 * 
 * @author <a href="mailto:yushun.qin@midea.com">Yushun.Qin</a>
 * @createTime 2017年8月26日 上午10:52:47
 * @since JDK1.7
 * @version 1.0.0
 *
 */
@SpringBootTest
public class EasymockTest {

	@Test
	public void test() {
		User expectedUser = new User("easy",18);
		expectedUser.setId("1001");
		UserService mock = EasyMock.createMock(UserService.class);// 创建Mock对象
		EasyMock.expect(mock.findUserById("1001")).andReturn(expectedUser);// 录制Mock对象预期行为
		EasyMock.replay(mock);// 重放Mock对象，测试时以录制的对象预期行为代替真实对象的行为
		assertEquals(expectedUser, mock.findUserById("1001")); // 断言测试结果
		EasyMock.verify(mock);// 验证Mock对象被调用
	}
}
