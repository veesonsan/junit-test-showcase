/**  
 * Copyright (c) 2016-2026,Pay1pay Technology Co., Ltd. All rights reserved. 
 *
 */

package com.midea.junit.test.powermock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.util.StringUtils;

/**
 * 
 * Powermock 模拟静态
 * 
 * @author <a href="mailto:yushun.qin@midea.com">Yushun.Qin</a>
 * @createTime 2017年8月26日 上午10:52:47
 * @since JDK1.7
 * @version 1.0.0
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(StringUtils.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PowermockStaticClassTest {
	
	@Test
	public void test1 () {
		//模拟的静态类
		PowerMockito.mockStatic(StringUtils.class);
		PowerMockito.mock(StringUtils.class);
		PowerMockito.when(StringUtils.hasText("ssss")).thenReturn(false);
		
		assertFalse(StringUtils.hasText("ssss"));
	}
	
	@Test
	public void test2 () {
		assertTrue(StringUtils.hasText("ssss"));
	}
	
	/**
	 * @PrepareForTest 的类必须与静态mock类一样 否则如下测试运行会抛异常
	 */
//	@Test
//	public void test3 () {
//		PowerMockito.mockStatic(org.apache.tomcat.util.codec.binary.StringUtils.class);
//		PowerMockito.when(org.apache.tomcat.util.codec.binary.StringUtils.newStringUtf8("ssss".getBytes())).thenReturn("haha");
//		
//		assertEquals("haha",org.apache.tomcat.util.codec.binary.StringUtils.newStringUtf8("ssss".getBytes()));
//	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test  
	public void spyTest2() {  
	      
	    List list = new LinkedList();  
	    List spy = Mockito.spy(list);  
	    
	    //optionally, you can stub out some methods:  
	    Mockito.when(spy.size()).thenReturn(100);  
	    
	    //using the spy calls real methods  
	    spy.add("one");  
	    spy.add("two");  
	    
	    //prints "one" - the first element of a list  
	    System.out.println(spy.get(0));  
	    
	    //size() method was stubbed - 100 is printed  
	    System.out.println(spy.size());  
	    
	    //optionally, you can verify  
	    Mockito.verify(spy).add("one");  
	    Mockito.verify(spy).add("two");   
	}
	
}
