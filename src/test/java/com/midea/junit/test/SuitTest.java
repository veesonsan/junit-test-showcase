/**  
 * Copyright (c) 2016-2026,Pay1pay Technology Co., Ltd. All rights reserved. 
 *
 */

package com.midea.junit.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.midea.junit.test.job.ScheduledTasksTest;
import com.midea.junit.test.service.UserServiceImplTest;
import com.midea.junit.test.web.UserControllerTest;

/**
 * JUnit套件测试,运行user相关测试类
 * 
 * @author <a href="mailto:yushun.qin@midea.com">Yushun.Qin</a>
 * @createTime 2017年8月30日 上午11:35:50
 * @since JDK1.7
 * @version 1.0.0
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ UserServiceImplTest.class, UserControllerTest.class, ScheduledTasksTest.class })
public class SuitTest {

}
