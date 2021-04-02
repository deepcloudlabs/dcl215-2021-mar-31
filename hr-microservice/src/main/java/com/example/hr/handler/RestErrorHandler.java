package com.example.hr.handler;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.hr.boundary.RestErrorMessage;

@RestControllerAdvice
public class RestErrorHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public RestErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		String reason = e.getAllErrors().stream().map(err -> err.getDefaultMessage()).collect(Collectors.joining(","));
		return new RestErrorMessage("failed",reason);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public RestErrorMessage handleConstraintViolationException(ConstraintViolationException e) {
		String reason = e.getConstraintViolations()
				         .stream()
				         .map(ConstraintViolation::getMessage)
				         .collect(Collectors.joining(","));
		return new RestErrorMessage("failed",reason);
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	public RestErrorMessage handleRuntimeException(RuntimeException e) {
		return new RestErrorMessage("failed", e.getMessage());
	}
	
}
