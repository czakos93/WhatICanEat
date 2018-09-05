package pl.dawidbronczak.spring.WhatICanEat.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pl.dawidbronczak.spring.WhatICanEat.exception.ResourceAlreadyExistException;
import pl.dawidbronczak.spring.WhatICanEat.exception.ResourceNotFoundException;

@RestControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler(ResourceAlreadyExistException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	String ingredientExistHandler(ResourceAlreadyExistException exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String ingredientNotFoundHandler(ResourceNotFoundException exception) {
		return exception.getMessage();
	}
}
