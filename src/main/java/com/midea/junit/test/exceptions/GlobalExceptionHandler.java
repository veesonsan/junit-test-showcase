package com.midea.junit.test.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public @ResponseBody String handleBusinessException(BusinessException ex) {
		return String.format("[%s]%s", ex.getErrCode(), ex.getErrMsg());
	}

}
