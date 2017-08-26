package com.midea.junit.test.domain;


public enum ErrorCodeEnum {
	UNKNOWN_ERROR("10001", "未知错误"),
	PARAM_ERROR("10002", "参数错误"),
	;
	
	private String errorCode;
	private String errMsg;

	ErrorCodeEnum(String errorCode, String errMsg) {
		this.errorCode = errorCode;
		this.errMsg = errMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrMsg() {
		return errMsg;
	}
}
