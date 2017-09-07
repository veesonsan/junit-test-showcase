/**  
 * Copyright (c) 2016-2026,Pay1pay Technology Co., Ltd. All rights reserved. 
 *
 */

package com.midea.junit.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * 
 * 参数化测试
 * 
 * @author <a href="mailto:yushun.qin@midea.com">Yushun.Qin</a>
 * @createTime 2017年8月25日 下午8:29:50
 * @since JDK1.7
 * @version 1.0.0
 *
 */
@RunWith(value = Parameterized.class)
public class ParameterizedTest {
	/** 网址正则表达式 */
	private static final String DOMAIN_NAME_PATTERN = "^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$";
	
	private String domain;
	private boolean expected;

	public ParameterizedTest(String domain, boolean expected) {
		this.domain = domain;
		this.expected = expected;
	}
	
// 也可以通过注解
//	@Parameterized.Parameter(0)
//	public String domain;
//	@Parameterized.Parameter(1)
//	public boolean expected;
	
	@Parameterized.Parameters(name = "{index}: isValidDomain({0})={1}")
	public static Iterable<Object[]> data() {
		return Arrays.asList(new Object[][] { { "google.com", true }, { "paypay.com", true }, { "-midea.com", false },
			{ "paypay-.com", false }, { "3423kjk", false }, { "mk#$kdo.com", false } });
	}
	
	@Test
	public void testValidDomains() {
		assertEquals(expected, domain.matches(DOMAIN_NAME_PATTERN));
	}
	
}
