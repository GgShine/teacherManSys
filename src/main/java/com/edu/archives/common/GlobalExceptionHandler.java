package com.edu.archives.common;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public Result handleIllegalArgument(IllegalArgumentException ex) {
		return Result.error("400", ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Result handleValidation(MethodArgumentNotValidException ex) {
		return Result.error("400", "请求参数不合法");
	}

	@ExceptionHandler(Exception.class)
	public Result handleException(Exception ex) {
		return Result.error("500", ex.getMessage() == null ? "服务器内部异常" : ex.getMessage());
	}
}
