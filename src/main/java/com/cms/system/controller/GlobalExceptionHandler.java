package com.cms.system.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cms.system.util.JsonResult;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public @ResponseBody JsonResult handleBusinessException(Exception ex) {
		JsonResult result = new JsonResult();
		result.setResult(1);
		result.setMessage(ex.getMessage());
		return result;
	}

}
