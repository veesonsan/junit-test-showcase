/**  
 * Copyright (c) 2016-2026,Pay1pay Technology Co., Ltd. All rights reserved. 
 *
 */

package com.midea.junit.test.job;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.midea.junit.test.domain.User;
import com.midea.junit.test.service.UserService;

/**
 * 
 * Job测试类
 * 
 * @author <a href="mailto:yushun.qin@midea.com">Yushun.Qin</a>
 * @createTime 2017年8月26日 上午10:52:47
 * @since JDK1.7
 * @version 1.0.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ScheduledTasksTest {

	@Autowired
	private ScheduledTasks scheduledTasks;
	@Autowired
	private UserService userService;
	/**
	 * 测试姓名
	 */
	private final static String TEST_NAME = "test_name";

	@Test
	public void test() {
		// 1.准备测试环境
		ScheduledTasks.USER_QUEUE.clear();
		userService.save(new User(TEST_NAME, 17));

		// 2.查找年龄小于18岁的user,并发如队列
		scheduledTasks.findPupil();

		// 3.校验队列user数量大于1
		int initSize = ScheduledTasks.USER_QUEUE.size();
		assertTrue(initSize >= 1);

		// 4.执行删除任务方法
		scheduledTasks.deletePupil();

		// 5.校验队列任务至少减少1个
		int afterSize = ScheduledTasks.USER_QUEUE.size();
		assertTrue((initSize - afterSize) >= 1);
	}

}
