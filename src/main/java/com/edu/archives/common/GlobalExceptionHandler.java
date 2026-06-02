package com.edu.archives.common;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 全局异常处理器。
// 调用关系：Controller 抛出异常后由 Spring 自动路由到对应 @ExceptionHandler 方法。
@RestControllerAdvice
public class GlobalExceptionHandler {

	// 处理参数非法异常。
	@ExceptionHandler(IllegalArgumentException.class)
	public Result handleIllegalArgument(IllegalArgumentException ex) {
		return Result.error("400", ex.getMessage());
	}

	// 处理参数校验异常。
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Result handleValidation(MethodArgumentNotValidException ex) {
		return Result.error("400", "请求参数不合法");
	}

	// 兜底处理其它未捕获异常。
	@ExceptionHandler(Exception.class)
	public Result handleException(Exception ex) {
		return Result.error("500", ex.getMessage() == null ? "服务器内部异常" : ex.getMessage());
	}
}
