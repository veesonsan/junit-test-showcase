package com.midea.junit.test.util;

import com.midea.junit.test.domain.ErrorCodeEnum;
import com.midea.junit.test.exceptions.BusinessException;

public class AssertUtil {
	private AssertUtil() {
	}

	/**
	 * 
	 * @param string
	 * @author Yushun.Qin on 2017年8月26日 上午9:12:12
	 */
	public static void notEmpty(String string) {
		if (string == null || string.trim().length() == 0) {
			throw new BusinessException(ErrorCodeEnum.PARAM_ERROR);
		}

	}

	/**
	 * @param object
	 * @author Yushun.Qin on 2017年8月26日 上午9:11:59
	 */
	public static void notEmpty(Object object) {
		if (object == null) {
			throw new BusinessException(ErrorCodeEnum.PARAM_ERROR.getErrorCode(), "对象为空");
		}
	}

}
