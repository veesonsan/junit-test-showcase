package com.midea.junit.test.exceptions;

import com.midea.junit.test.domain.ErrorCodeEnum;

@SuppressWarnings("serial")
public class BusinessException extends RuntimeException {
	/**
	 * 错误码
	 */
	private final String errCode;

	/**
	 * 错误信息
	 */
	private  final String errMsg;

	public BusinessException(String errCode, String errMsg) {
		super(String.format("[%s]%s", errCode, errMsg));
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public BusinessException(ErrorCodeEnum errorCodeEnum) {
		super(String.format("[%s]%s", errorCodeEnum.getErrorCode(), errorCodeEnum.getErrMsg()));
		this.errCode = errorCodeEnum.getErrorCode();
		this.errMsg = errorCodeEnum.getErrMsg();
	}

	public String getErrCode() {
		return errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

}