package pl.dawidbronczak.spring.WhatICanEat.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import pl.dawidbronczak.spring.WhatICanEat.exception.IngredientExistException;
import pl.dawidbronczak.spring.WhatICanEat.exception.IngredientNotFoundException;

@RestControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler(IngredientExistException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	String ingredientExistHandler(IngredientExistException exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler(IngredientNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String ingredientNotFoundHandler(IngredientNotFoundException exception) {
		return exception.getMessage();
	}
}
